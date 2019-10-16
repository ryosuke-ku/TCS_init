    public Version checkin(Calendar created) throws RepositoryException {
        return getVersionManagerImpl().checkin(getPath(), created);
    }
