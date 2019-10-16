    public void testCleanup() throws IOException {
        // Load some photos into the store.
        Set<Long> photoFileIds = new HashSet<Long>();
        Map<Integer, Long> resourceIdToPhotoMap = new HashMap<Integer, Long>();
        int[] resourceIds = new int[] {
                R.drawable.earth_normal, R.drawable.earth_large, R.drawable.earth_huge
        };
        for (int resourceId : resourceIds) {
            long photoFileId = mPhotoStore.insert(
                    new PhotoProcessor(loadPhotoFromResource(resourceId, PhotoSize.ORIGINAL),
                            256, 96));
            resourceIdToPhotoMap.put(resourceId, photoFileId);
            photoFileIds.add(photoFileId);
        }
        assertFalse(photoFileIds.contains(0L));
        assertEquals(3, photoFileIds.size());

        // Run cleanup with the indication that only the large and huge photos are in use, along
        // with a bogus photo file ID that isn't in the photo store.
        long bogusPhotoFileId = 123456789;
        Set<Long> photoFileIdsInUse = new HashSet<Long>();
        photoFileIdsInUse.add(resourceIdToPhotoMap.get(R.drawable.earth_large));
        photoFileIdsInUse.add(resourceIdToPhotoMap.get(R.drawable.earth_huge));
        photoFileIdsInUse.add(bogusPhotoFileId);

        Set<Long> photoIdsToCleanup = mPhotoStore.cleanup(photoFileIdsInUse);

        // The set of photo IDs to clean up should consist of the bogus photo file ID.
        assertEquals(1, photoIdsToCleanup.size());
        assertTrue(photoIdsToCleanup.contains(bogusPhotoFileId));

        // The entry for the normal-sized photo should have been cleaned up, since it isn't being
        // used.
        long normalPhotoId = resourceIdToPhotoMap.get(R.drawable.earth_normal);
        assertNull(mPhotoStore.get(normalPhotoId));

        // Check that the database record has also been removed.
        Cursor c = mDb.query(Tables.PHOTO_FILES, new String[]{PhotoFiles._ID},
                PhotoFiles._ID + "=?", new String[]{String.valueOf(normalPhotoId)},
                null, null, null);
        try {
            assertEquals(0, c.getCount());
        } finally {
            c.close();
        }
    }
