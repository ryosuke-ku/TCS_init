    public long insertStatusUpdate(ContentValues values) {
        final String handle = values.getAsString(StatusUpdates.IM_HANDLE);
        final Integer protocol = values.getAsInteger(StatusUpdates.PROTOCOL);
        String customProtocol = null;

        if (protocol != null && protocol == Im.PROTOCOL_CUSTOM) {
            customProtocol = values.getAsString(StatusUpdates.CUSTOM_PROTOCOL);
            if (TextUtils.isEmpty(customProtocol)) {
                throw new IllegalArgumentException(
                        "CUSTOM_PROTOCOL is required when PROTOCOL=PROTOCOL_CUSTOM");
            }
        }

        long rawContactId = -1;
        long contactId = -1;
        Long dataId = values.getAsLong(StatusUpdates.DATA_ID);
        String accountType = null;
        String accountName = null;
        mSb.setLength(0);
        mSelectionArgs.clear();
        if (dataId != null) {
            // Lookup the contact info for the given data row.

            mSb.append(Tables.DATA + "." + Data._ID + "=?");
            mSelectionArgs.add(String.valueOf(dataId));
        } else {
            // Lookup the data row to attach this presence update to

            if (TextUtils.isEmpty(handle) || protocol == null) {
                throw new IllegalArgumentException("PROTOCOL and IM_HANDLE are required");
            }

            // TODO: generalize to allow other providers to match against email
            boolean matchEmail = Im.PROTOCOL_GOOGLE_TALK == protocol;

            String mimeTypeIdIm = String.valueOf(mDbHelper.get().getMimeTypeIdForIm());
            if (matchEmail) {
                String mimeTypeIdEmail = String.valueOf(mDbHelper.get().getMimeTypeIdForEmail());

                // The following hack forces SQLite to use the (mimetype_id,data1) index, otherwise
                // the "OR" conjunction confuses it and it switches to a full scan of
                // the raw_contacts table.

                // This code relies on the fact that Im.DATA and Email.DATA are in fact the same
                // column - Data.DATA1
                mSb.append(DataColumns.MIMETYPE_ID + " IN (?,?)" +
                        " AND " + Data.DATA1 + "=?" +
                        " AND ((" + DataColumns.MIMETYPE_ID + "=? AND " + Im.PROTOCOL + "=?");
                mSelectionArgs.add(mimeTypeIdEmail);
                mSelectionArgs.add(mimeTypeIdIm);
                mSelectionArgs.add(handle);
                mSelectionArgs.add(mimeTypeIdIm);
                mSelectionArgs.add(String.valueOf(protocol));
                if (customProtocol != null) {
                    mSb.append(" AND " + Im.CUSTOM_PROTOCOL + "=?");
                    mSelectionArgs.add(customProtocol);
                }
                mSb.append(") OR (" + DataColumns.MIMETYPE_ID + "=?))");
                mSelectionArgs.add(mimeTypeIdEmail);
            } else {
                mSb.append(DataColumns.MIMETYPE_ID + "=?" +
                        " AND " + Im.PROTOCOL + "=?" +
                        " AND " + Im.DATA + "=?");
                mSelectionArgs.add(mimeTypeIdIm);
                mSelectionArgs.add(String.valueOf(protocol));
                mSelectionArgs.add(handle);
                if (customProtocol != null) {
                    mSb.append(" AND " + Im.CUSTOM_PROTOCOL + "=?");
                    mSelectionArgs.add(customProtocol);
                }
            }

            if (values.containsKey(StatusUpdates.DATA_ID)) {
                mSb.append(" AND " + DataColumns.CONCRETE_ID + "=?");
                mSelectionArgs.add(values.getAsString(StatusUpdates.DATA_ID));
            }
        }

        Cursor cursor = null;
        try {
            cursor = mActiveDb.get().query(DataContactsQuery.TABLE, DataContactsQuery.PROJECTION,
                    mSb.toString(), mSelectionArgs.toArray(EMPTY_STRING_ARRAY), null, null,
                    Clauses.CONTACT_VISIBLE + " DESC, " + Data.RAW_CONTACT_ID);
            if (cursor.moveToFirst()) {
                dataId = cursor.getLong(DataContactsQuery.DATA_ID);
                rawContactId = cursor.getLong(DataContactsQuery.RAW_CONTACT_ID);
                accountType = cursor.getString(DataContactsQuery.ACCOUNT_TYPE);
                accountName = cursor.getString(DataContactsQuery.ACCOUNT_NAME);
                contactId = cursor.getLong(DataContactsQuery.CONTACT_ID);
            } else {
                // No contact found, return a null URI
                return -1;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        if (values.containsKey(StatusUpdates.PRESENCE)) {
            if (customProtocol == null) {
                // We cannot allow a null in the custom protocol field, because SQLite3 does not
                // properly enforce uniqueness of null values
                customProtocol = "";
            }

            mValues.clear();
            mValues.put(StatusUpdates.DATA_ID, dataId);
            mValues.put(PresenceColumns.RAW_CONTACT_ID, rawContactId);
            mValues.put(PresenceColumns.CONTACT_ID, contactId);
            mValues.put(StatusUpdates.PROTOCOL, protocol);
            mValues.put(StatusUpdates.CUSTOM_PROTOCOL, customProtocol);
            mValues.put(StatusUpdates.IM_HANDLE, handle);
            if (values.containsKey(StatusUpdates.IM_ACCOUNT)) {
                mValues.put(StatusUpdates.IM_ACCOUNT, values.getAsString(StatusUpdates.IM_ACCOUNT));
            }
            mValues.put(StatusUpdates.PRESENCE,
                    values.getAsString(StatusUpdates.PRESENCE));
            mValues.put(StatusUpdates.CHAT_CAPABILITY,
                    values.getAsString(StatusUpdates.CHAT_CAPABILITY));

            // Insert the presence update
            mActiveDb.get().replace(Tables.PRESENCE, null, mValues);
        }


        if (values.containsKey(StatusUpdates.STATUS)) {
            String status = values.getAsString(StatusUpdates.STATUS);
            String resPackage = values.getAsString(StatusUpdates.STATUS_RES_PACKAGE);
            Resources resources = getContext().getResources();
            if (!TextUtils.isEmpty(resPackage)) {
                PackageManager pm = getContext().getPackageManager();
                try {
                    resources = pm.getResourcesForApplication(resPackage);
                } catch (NameNotFoundException e) {
                    Log.w(TAG, "Contact status update resource package not found: "
                            + resPackage);
                }
            }
            Integer labelResourceId = values.getAsInteger(StatusUpdates.STATUS_LABEL);

            if ((labelResourceId == null || labelResourceId == 0) && protocol != null) {
                labelResourceId = Im.getProtocolLabelResource(protocol);
            }
            String labelResource = getResourceName(resources, "string", labelResourceId);

            Integer iconResourceId = values.getAsInteger(StatusUpdates.STATUS_ICON);
            // TODO compute the default icon based on the protocol

            String iconResource = getResourceName(resources, "drawable", iconResourceId);

            if (TextUtils.isEmpty(status)) {
                mDbHelper.get().deleteStatusUpdate(dataId);
            } else {
                Long timestamp = values.getAsLong(StatusUpdates.STATUS_TIMESTAMP);
                if (timestamp != null) {
                    mDbHelper.get().replaceStatusUpdate(dataId, timestamp, status, resPackage,
                            iconResourceId, labelResourceId);
                } else {
                    mDbHelper.get().insertStatusUpdate(dataId, status, resPackage, iconResourceId,
                            labelResourceId);
                }

                // For forward compatibility with the new stream item API, insert this status update
                // there as well.  If we already have a stream item from this source, update that
                // one instead of inserting a new one (since the semantics of the old status update
                // API is to only have a single record).
                if (rawContactId != -1 && !TextUtils.isEmpty(status)) {
                    ContentValues streamItemValues = new ContentValues();
                    streamItemValues.put(StreamItems.RAW_CONTACT_ID, rawContactId);
                    // Status updates are text only but stream items are HTML.
                    streamItemValues.put(StreamItems.TEXT, statusUpdateToHtml(status));
                    streamItemValues.put(StreamItems.COMMENTS, "");
                    streamItemValues.put(StreamItems.RES_PACKAGE, resPackage);
                    streamItemValues.put(StreamItems.RES_ICON, iconResource);
                    streamItemValues.put(StreamItems.RES_LABEL, labelResource);
                    streamItemValues.put(StreamItems.TIMESTAMP,
                            timestamp == null ? System.currentTimeMillis() : timestamp);

                    // Note: The following is basically a workaround for the fact that status
                    // updates didn't do any sort of account enforcement, while social stream item
                    // updates do.  We can't expect callers of the old API to start passing account
                    // information along, so we just populate the account params appropriately for
                    // the raw contact.  Data set is not relevant here, as we only check account
                    // name and type.
                    if (accountName != null && accountType != null) {
                        streamItemValues.put(RawContacts.ACCOUNT_NAME, accountName);
                        streamItemValues.put(RawContacts.ACCOUNT_TYPE, accountType);
                    }

                    // Check for an existing stream item from this source, and insert or update.
                    Uri streamUri = StreamItems.CONTENT_URI;
                    Cursor c = queryLocal(streamUri, new String[]{StreamItems._ID},
                            StreamItems.RAW_CONTACT_ID + "=?",
                            new String[]{String.valueOf(rawContactId)},
                            null, -1 /* directory ID */, null);
                    try {
                        if (c.getCount() > 0) {
                            c.moveToFirst();
                            updateInTransaction(ContentUris.withAppendedId(streamUri, c.getLong(0)),
                                    streamItemValues, null, null);
                        } else {
                            insertInTransaction(streamUri, streamItemValues);
                        }
                    } finally {
                        c.close();
                    }
                }
            }
        }

        if (contactId != -1) {
            mAggregator.get().updateLastStatusUpdateId(contactId);
        }

        return dataId;
    }
