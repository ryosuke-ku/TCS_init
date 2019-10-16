    public void testQueryStreamItemPhotoWithSelection() {
        long rawContactId = createRawContact();
        ContentValues values = buildGenericStreamItemValues();
        Uri resultUri = insertStreamItem(rawContactId, values, null);
        long streamItemId = ContentUris.parseId(resultUri);

        ContentValues photo1Values = buildGenericStreamItemPhotoValues(1);
        insertStreamItemPhoto(streamItemId, photo1Values, null);
        photo1Values.remove(StreamItemPhotos.PHOTO);  // Removed during processing.
        ContentValues photo2Values = buildGenericStreamItemPhotoValues(2);
        insertStreamItemPhoto(streamItemId, photo2Values, null);

        // Select only the first photo.
        assertStoredValues(StreamItems.CONTENT_PHOTO_URI, StreamItemPhotos.SORT_INDEX + "=?",
                new String[]{"1"}, photo1Values);
    }
