    public void testDeleteStreamItemPhotoById() {
        long rawContactId = RawContactUtil.createRawContact(mResolver);
        long streamItemId = ContentUris.parseId(
                insertStreamItem(rawContactId, buildGenericStreamItemValues(), null));
        long streamItemPhotoId = ContentUris.parseId(
                insertStreamItemPhoto(streamItemId, buildGenericStreamItemPhotoValues(0), null));
        mResolver.delete(
                ContentUris.withAppendedId(
                        Uri.withAppendedPath(
                                ContentUris.withAppendedId(StreamItems.CONTENT_URI, streamItemId),
                                StreamItems.StreamItemPhotos.CONTENT_DIRECTORY),
                        streamItemPhotoId), null, null);

        Cursor c = mResolver.query(StreamItems.CONTENT_PHOTO_URI,
                new String[]{StreamItemPhotos._ID},
                StreamItemPhotos.STREAM_ITEM_ID + "=?", new String[]{String.valueOf(streamItemId)},
                null);
        try {
            assertEquals("Expected photo to be deleted.", 0, c.getCount());
        } finally {
            c.close();
        }
    }
