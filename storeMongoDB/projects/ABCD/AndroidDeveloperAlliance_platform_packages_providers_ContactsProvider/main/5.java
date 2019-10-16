    public long insert(PhotoProcessor photoProcessor, boolean allowSmallImageStorage) {
        Bitmap displayPhoto = photoProcessor.getDisplayPhoto();
        int width = displayPhoto.getWidth();
        int height = displayPhoto.getHeight();
        int thumbnailDim = photoProcessor.getMaxThumbnailPhotoDim();
        if (allowSmallImageStorage || width > thumbnailDim || height > thumbnailDim) {
            // Write the photo to a temp file, create the DB record for tracking it, and rename the
            // temp file to match.
            File file = null;
            try {
                // Write the display photo to a temp file.
                byte[] photoBytes = photoProcessor.getDisplayPhotoBytes();
                file = File.createTempFile("img", null, mStorePath);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(photoBytes);
                fos.close();

                // Create the DB entry.
                ContentValues values = new ContentValues();
                values.put(PhotoFiles.HEIGHT, height);
                values.put(PhotoFiles.WIDTH, width);
                values.put(PhotoFiles.FILESIZE, photoBytes.length);
                long id = mDb.insert(Tables.PHOTO_FILES, null, values);
                if (id != 0) {
                    // Rename the temp file.
                    File target = getFileForPhotoFileId(id);
                    if (file.renameTo(target)) {
                        Entry entry = new Entry(target);
                        putEntry(entry.id, entry);
                        return id;
                    }
                }
            } catch (IOException e) {
                // Write failed - will delete the file below.
            }

            // If anything went wrong, clean up the file before returning.
            if (file != null) {
                cleanupFile(file);
            }
        }
        return 0;
    }
