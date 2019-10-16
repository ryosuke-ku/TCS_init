    public void autoClose() throws Exception {
        final String directory = root.getRoot().getAbsolutePath();
        try (FileBucket bucket = new FileBucket(new PrintWriter(System.out), directory, this.closeable);) {
            bucket.begin();
        }
        verify(this.closeable).close();
    }
