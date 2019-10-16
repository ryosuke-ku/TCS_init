    private long insertStreamItemPhoto(Uri uri, ContentValues values) {
        long id = 0;
        mValues.clear();
        mValues.putAll(values);

        long streamItemId = mValues.getAsLong(StreamItemPhotos.STREAM_ITEM_ID);
        if (streamItemId != 0) {
            long rawContactId = lookupRawContactIdForStreamId(streamItemId);

            // Don't attempt to insert accounts params - they don't exist in the stream item
            // photos table.
            mValues.remove(RawContacts.ACCOUNT_NAME);
            mValues.remove(RawContacts.ACCOUNT_TYPE);

            // Process the photo and store it.
            if (processStreamItemPhoto(mValues, false)) {
                // Insert the stream item photo.
                id = mActiveDb.get().insert(Tables.STREAM_ITEM_PHOTOS, null, mValues);
            }
        }
        return id;
    }
