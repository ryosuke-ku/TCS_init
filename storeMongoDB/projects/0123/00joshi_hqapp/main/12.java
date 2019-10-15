    public String[] getIncludedDirectories() {
        String[] directories;
        synchronized (this) {
            if (dirsIncluded == null) {
                throw new IllegalStateException("Must call scan() first");
            }
            directories = dirsIncluded.toArray(new String[dirsIncluded.size()]);
        }
        Arrays.sort(directories);
        return directories;
    }
