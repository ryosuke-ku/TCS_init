    public File getTempDir() {
        Object o = getProperties().get(TEMP_DIR);
        if (o instanceof File) {
            return (File) o;
        } else if (o instanceof String) {
            return new File(o.toString());
        }
        return null;
    }
