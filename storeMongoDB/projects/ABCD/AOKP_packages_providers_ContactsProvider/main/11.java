    private long insertGroup(Uri uri, ContentValues values, boolean callerIsSyncAdapter) {
        mValues.clear();
        mValues.putAll(values);

        final long accountId = mDbHelper.get().getOrCreateAccountIdInTransaction(
                resolveAccountWithDataSet(uri, mValues));
        mValues.remove(Groups.ACCOUNT_NAME);
        mValues.remove(Groups.ACCOUNT_TYPE);
        mValues.remove(Groups.DATA_SET);
        mValues.put(GroupsColumns.ACCOUNT_ID, accountId);

        // Replace package with internal mapping
        final String packageName = mValues.getAsString(Groups.RES_PACKAGE);
        if (packageName != null) {
            mValues.put(GroupsColumns.PACKAGE_ID, mDbHelper.get().getPackageId(packageName));
        }
        mValues.remove(Groups.RES_PACKAGE);

        final boolean isFavoritesGroup = flagIsSet(mValues, Groups.FAVORITES);

        if (!callerIsSyncAdapter) {
            mValues.put(Groups.DIRTY, 1);
        }

        final SQLiteDatabase db = mDbHelper.get().getWritableDatabase();

        long result = db.insert(Tables.GROUPS, Groups.TITLE, mValues);

        if (!callerIsSyncAdapter && isFavoritesGroup) {
            // If the inserted group is a favorite group, add all starred raw contacts to it.
            mSelectionArgs1[0] = Long.toString(accountId);
            Cursor c = db.query(Tables.RAW_CONTACTS,
                    new String[]{RawContacts._ID, RawContacts.STARRED},
                    RawContactsColumns.CONCRETE_ACCOUNT_ID + "=?", mSelectionArgs1,
                    null, null, null);
            try {
                while (c.moveToNext()) {
                    if (c.getLong(1) != 0) {
                        final long rawContactId = c.getLong(0);
                        insertDataGroupMembership(rawContactId, result);
                        mTransactionContext.get().markRawContactDirtyAndChanged(rawContactId,
                                callerIsSyncAdapter);
                    }
                }
            } finally {
                c.close();
            }
        }

        if (mValues.containsKey(Groups.GROUP_VISIBLE)) {
            mVisibleTouched = true;
        }

        return result;
    }
