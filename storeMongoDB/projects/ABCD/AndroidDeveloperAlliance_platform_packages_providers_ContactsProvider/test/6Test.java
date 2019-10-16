    public void testRemoveEntry() throws IOException {
        byte[] photo = loadPhotoFromResource(R.drawable.earth_normal, PhotoSize.ORIGINAL);
        long photoFileId = mPhotoStore.insert(newPhotoProcessor(photo, false));
        PhotoStore.Entry entry = mPhotoStore.get(photoFileId);
        assertTrue(new File(entry.path).exists());

        mPhotoStore.remove(photoFileId);

        // Check that the file has been deleted.
        assertFalse(new File(entry.path).exists());

        // Check that the database record has also been removed.
        Cursor c = mDb.query(Tables.PHOTO_FILES, new String[]{PhotoFiles._ID},
                PhotoFiles._ID + "=?", new String[]{String.valueOf(photoFileId)}, null, null, null);
        try {
            assertEquals(0, c.getCount());
        } finally {
            c.close();
        }
    }
