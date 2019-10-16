    public File[] listFiles(FileFilter filter) {
        File[] files = listFiles();
        if (filter == null || files == null) {
            return files;
        }
        List<File> result = new ArrayList<File>(files.length);
        for (File file : files) {
            if (filter.accept(file)) {
                result.add(file);
            }
        }
        return result.toArray(new File[result.size()]);
    }
