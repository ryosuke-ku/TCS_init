    private long insertStreamItemPhoto(Uri uri, ContentValues inputValues) {
        final Long streamItemId = inputValues.getAsLong(StreamItemPhotos.STREAM_ITEM_ID);
        if (streamItemId == null || streamItemId == 0) {
            return 0;
        }

        // The input seem valid, create a shallow copy.
        final ContentValues values = new ContentValues(inputValues);

        // Update the relevant values before inserting the new entry into the database.  The
        // account parameters are not added since they don't exist in the stream items table.
        values.remove(RawContacts.ACCOUNT_NAME);
        values.remove(RawContacts.ACCOUNT_TYPE);

        // Attempt to process and store the photo.
        if (!processStreamItemPhoto(values, false)) {
            return 0;
        }

        // Insert the new entry and return its ID.
        final SQLiteDatabase db = mDbHelper.get().getWritableDatabase();
        return db.insert(Tables.STREAM_ITEM_PHOTOS, null, values);
    }
