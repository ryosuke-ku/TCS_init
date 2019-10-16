    public String getAbsolutePath() {
        if (isAbsolute()) {
            return path;
        }
        String userDir = System.getProperty("user.dir");
        return path.isEmpty() ? userDir : join(userDir, path);
    }
