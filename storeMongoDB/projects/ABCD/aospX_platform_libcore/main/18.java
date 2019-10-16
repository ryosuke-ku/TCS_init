    public boolean setLastModified(long time) {
        if (time < 0) {
            throw new IllegalArgumentException("time < 0");
        }
        return setLastModifiedImpl(path, time);
    }
