    public synchronized void setExcludes(final String[] excludes) {
        if (excludes == null) {
            this.excludes = null;
        } else {
            this.excludes = Stream.of(excludes).map(DirectoryScanner::normalizePattern)
                    .toArray(String[]::new);
        }
    }
