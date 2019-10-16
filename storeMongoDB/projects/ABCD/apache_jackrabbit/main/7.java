    public boolean hasProperty(String relPath) throws RepositoryException {
        // check state of this instance
        sanityCheck();

        PropertyId id = resolveRelativePropertyPath(relPath);
        if (id != null) {
            return itemMgr.itemExists(id);
        } else {
            return false;
        }
    }
