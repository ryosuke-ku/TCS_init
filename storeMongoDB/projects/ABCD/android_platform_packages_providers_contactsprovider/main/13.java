    private long insertStreamItem(Uri uri, ContentValues inputValues) {
        Long rawContactId = inputValues.getAsLong(Data.RAW_CONTACT_ID);
        if (rawContactId == null) {
            throw new IllegalArgumentException(Data.RAW_CONTACT_ID + " is required");
        }

        // The input seem valid, create a shallow copy.
        final ContentValues values = new ContentValues(inputValues);

        // Update the relevant values before inserting the new entry into the database.  The
        // account parameters are not added since they don't exist in the stream items table.
        values.remove(RawContacts.ACCOUNT_NAME);
        values.remove(RawContacts.ACCOUNT_TYPE);

        // Insert the new stream item.
        final SQLiteDatabase db = mDbHelper.get().getWritableDatabase();
        final long id = db.insert(Tables.STREAM_ITEMS, null, values);
        if (id == -1) {
            return 0;  // Insertion failed.
        }

        // Check to see if we're over the limit for stream items under this raw contact.
        // It's possible that the inserted stream item is older than the the existing
        // ones, in which case it may be deleted immediately (resetting the ID to 0).
        return cleanUpOldStreamItems(rawContactId, id);
    }
