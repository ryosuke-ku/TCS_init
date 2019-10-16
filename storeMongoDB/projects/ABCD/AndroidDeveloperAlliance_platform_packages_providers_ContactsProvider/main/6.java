    public void remove(long id) {
        cleanupFile(getFileForPhotoFileId(id));
        removeEntry(id);
    }
