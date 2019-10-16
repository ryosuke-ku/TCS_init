    private long insertStreamItem(Uri uri, ContentValues values) {
        long id = 0;
        mValues.clear();
        mValues.putAll(values);

        long rawContactId = mValues.getAsLong(StreamItems.RAW_CONTACT_ID);

        // Don't attempt to insert accounts params - they don't exist in the stream items table.
        mValues.remove(RawContacts.ACCOUNT_NAME);
        mValues.remove(RawContacts.ACCOUNT_TYPE);

        // Insert the new stream item.
        id = mActiveDb.get().insert(Tables.STREAM_ITEMS, null, mValues);
        if (id == -1) {
            // Insertion failed.
            return 0;
        }

        // Check to see if we're over the limit for stream items under this raw contact.
        // It's possible that the inserted stream item is older than the the existing
        // ones, in which case it may be deleted immediately (resetting the ID to 0).
        id = cleanUpOldStreamItems(rawContactId, id);

        return id;
    }
