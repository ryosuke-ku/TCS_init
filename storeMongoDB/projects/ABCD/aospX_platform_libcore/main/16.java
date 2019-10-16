    public boolean isHidden() {
        if (path.isEmpty()) {
            return false;
        }
        return getName().startsWith(".");
    }
