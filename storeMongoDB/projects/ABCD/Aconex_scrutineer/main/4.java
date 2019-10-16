    OutputStream createSortedOutputStream() {
        try {
            return new BufferedOutputStream(new FileOutputStream(sortedFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
