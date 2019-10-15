    public String[] getIncludedFiles() {
        String[] files;
        synchronized (this) {
            if (filesIncluded == null) {
                throw new IllegalStateException("Must call scan() first");
            }
            files = filesIncluded.toArray(new String[filesIncluded.size()]);
        }
        Arrays.sort(files);
        return files;
    }
