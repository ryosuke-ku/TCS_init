    public void commit() throws ResourceException {
        out.println("#FileBucket.commit " + toString());
        try {
            processDeletions();
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot delete files: " + ex, ex);
        }
        flushChanges();
    }
