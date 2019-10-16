    InputStream createUnSortedInputStream() {
        try {
            return new BufferedInputStream(new FileInputStream(unsortedFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
