    public boolean isGranted(@Nonnull TreeLocation location, long permissions) {
        if (isRead(permissions)) {
            Tree tree = getTreeFromLocation(location);
            if (tree != null) {
                return isGranted(tree, location.getProperty(), permissions);
            }
        }
        return false;
    }
