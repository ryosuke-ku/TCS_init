    public String[] list(FilenameFilter filter) {
        String[] filenames = list();
        if (filter == null || filenames == null) {
            return filenames;
        }
        List<String> result = new ArrayList<String>(filenames.length);
        for (String filename : filenames) {
            if (filter.accept(this, filename)) {
                result.add(filename);
            }
        }
        return result.toArray(new String[result.size()]);
    }
