    private long insertStatusUpdate(ContentValues inputValues) {
        final String handle = inputValues.getAsString(StatusUpdates.IM_HANDLE);
        final Integer protocol = inputValues.getAsInteger(StatusUpdates.PROTOCOL);
        String customProtocol = null;

        final ContactsDatabaseHelper dbHelper = mDbHelper.get();
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (protocol != null && protocol == Im.PROTOCOL_CUSTOM) {
            customProtocol = inputValues.getAsString(StatusUpdates.CUSTOM_PROTOCOL);
            if (TextUtils.isEmpty(customProtocol)) {
                throw new IllegalArgumentException(
                        "CUSTOM_PROTOCOL is required when PROTOCOL=PROTOCOL_CUSTOM");
            }
        }

        long rawContactId = -1;
        long contactId = -1;
        Long dataId = inputValues.getAsLong(StatusUpdates.DATA_ID);
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

            // TODO: generalize to allow other providers to match against email.
            boolean matchEmail = Im.PROTOCOL_GOOGLE_TALK == protocol;

            String mimeTypeIdIm = String.valueOf(dbHelper.getMimeTypeIdForIm());
            if (matchEmail) {
                String mimeTypeIdEmail = String.valueOf(dbHelper.getMimeTypeIdForEmail());

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

            final String dataID = inputValues.getAsString(StatusUpdates.DATA_ID);
            if (dataID != null) {
                mSb.append(" AND " + DataColumns.CONCRETE_ID + "=?");
                mSelectionArgs.add(dataID);
            }
        }

        Cursor cursor = null;
        try {
            cursor = db.query(DataContactsQuery.TABLE, DataContactsQuery.PROJECTION,
                    mSb.toString(), mSelectionArgs.toArray(EMPTY_STRING_ARRAY), null, null,
                    Clauses.CONTACT_VISIBLE + " DESC, " + Data.RAW_CONTACT_ID);
            if (cursor.moveToFirst()) {
                dataId = cursor.getLong(DataContactsQuery.DATA_ID);
                rawContactId = cursor.getLong(DataContactsQuery.RAW_CONTACT_ID);
                accountType = cursor.getString(DataContactsQuery.ACCOUNT_TYPE);
                accountName = cursor.getString(DataContactsQuery.ACCOUNT_NAME);
                contactId = cursor.getLong(DataContactsQuery.CONTACT_ID);
            } else {
                // No contact found, return a null URI.
                return -1;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        final String presence = inputValues.getAsString(StatusUpdates.PRESENCE);
        if (presence != null) {
            if (customProtocol == null) {
                // We cannot allow a null in the custom protocol field, because SQLite3 does not
                // properly enforce uniqueness of null values
                customProtocol = "";
            }

            final ContentValues values = new ContentValues();
            values.put(StatusUpdates.DATA_ID, dataId);
            values.put(PresenceColumns.RAW_CONTACT_ID, rawContactId);
            values.put(PresenceColumns.CONTACT_ID, contactId);
            values.put(StatusUpdates.PROTOCOL, protocol);
            values.put(StatusUpdates.CUSTOM_PROTOCOL, customProtocol);
            values.put(StatusUpdates.IM_HANDLE, handle);
            final String imAccount = inputValues.getAsString(StatusUpdates.IM_ACCOUNT);
            if (imAccount != null) {
                values.put(StatusUpdates.IM_ACCOUNT, imAccount);
            }
            values.put(StatusUpdates.PRESENCE, presence);
            values.put(StatusUpdates.CHAT_CAPABILITY,
                    inputValues.getAsString(StatusUpdates.CHAT_CAPABILITY));

            // Insert the presence update.
            db.replace(Tables.PRESENCE, null, values);
        }

        if (inputValues.containsKey(StatusUpdates.STATUS)) {
            String status = inputValues.getAsString(StatusUpdates.STATUS);
            String resPackage = inputValues.getAsString(StatusUpdates.STATUS_RES_PACKAGE);
            Resources resources = getContext().getResources();
            if (!TextUtils.isEmpty(resPackage)) {
                PackageManager pm = getContext().getPackageManager();
                try {
                    resources = pm.getResourcesForApplication(resPackage);
                } catch (NameNotFoundException e) {
                    Log.w(TAG, "Contact status update resource package not found: " + resPackage);
                }
            }
            Integer labelResourceId = inputValues.getAsInteger(StatusUpdates.STATUS_LABEL);

            if ((labelResourceId == null || labelResourceId == 0) && protocol != null) {
                labelResourceId = Im.getProtocolLabelResource(protocol);
            }
            String labelResource = getResourceName(resources, "string", labelResourceId);

            Integer iconResourceId = inputValues.getAsInteger(StatusUpdates.STATUS_ICON);
            // TODO compute the default icon based on the protocol

            String iconResource = getResourceName(resources, "drawable", iconResourceId);

            if (TextUtils.isEmpty(status)) {
                dbHelper.deleteStatusUpdate(dataId);
            } else {
                Long timestamp = inputValues.getAsLong(StatusUpdates.STATUS_TIMESTAMP);
                if (timestamp != null) {
                    dbHelper.replaceStatusUpdate(
                            dataId, timestamp, status, resPackage, iconResourceId, labelResourceId);
                } else {
                    dbHelper.insertStatusUpdate(
                            dataId, status, resPackage, iconResourceId, labelResourceId);
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
                    Cursor c = queryLocal(streamUri, new String[] {StreamItems._ID},
                            StreamItems.RAW_CONTACT_ID + "=?",
                            new String[] {String.valueOf(rawContactId)},
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
