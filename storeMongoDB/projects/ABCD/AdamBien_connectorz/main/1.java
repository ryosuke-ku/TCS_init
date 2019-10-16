    public void begin() throws ResourceException {
        out.println("#FileBucket.begin " + toString());
        this.createIfNotExists(this.rootDirectory);
    }
