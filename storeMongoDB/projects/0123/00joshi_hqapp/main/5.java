    public synchronized void setIncludes(final String[] includes) {
        if (includes == null) {
            this.includes = null;
        } else {
            this.includes = Stream.of(includes)
                .map(DirectoryScanner::normalizePattern).toArray(String[]::new);
        }
    }
