    private long insertGroup(Uri uri, ContentValues inputValues, boolean callerIsSyncAdapter) {
        // Create a shallow copy.
        final ContentValues values = new ContentValues(inputValues);

        // Populate the relevant values before inserting the new entry into the database.
        final long accountId = replaceAccountInfoByAccountId(uri, values);
        replacePackageNameByPackageId(values);
        if (!callerIsSyncAdapter) {
            values.put(Groups.DIRTY, 1);
        }

        // Insert the new entry.
        final SQLiteDatabase db = mDbHelper.get().getWritableDatabase();
        final long groupId = db.insert(Tables.GROUPS, Groups.TITLE, values);

        final boolean isFavoritesGroup = flagIsSet(values, Groups.FAVORITES);
        if (!callerIsSyncAdapter && isFavoritesGroup) {
            // Favorite group, add all starred raw contacts to it.
            mSelectionArgs1[0] = Long.toString(accountId);
            Cursor c = db.query(Tables.RAW_CONTACTS,
                    new String[] {RawContacts._ID, RawContacts.STARRED},
                    RawContactsColumns.CONCRETE_ACCOUNT_ID + "=?", mSelectionArgs1,
                    null, null, null);
            try {
                while (c.moveToNext()) {
                    if (c.getLong(1) != 0) {
                        final long rawContactId = c.getLong(0);
                        insertDataGroupMembership(rawContactId, groupId);
                        mTransactionContext.get().markRawContactDirtyAndChanged(
                                rawContactId, callerIsSyncAdapter);
                    }
                }
            } finally {
                c.close();
            }
        }

        if (values.containsKey(Groups.GROUP_VISIBLE)) {
            mVisibleTouched = true;
        }
        return groupId;
    }
