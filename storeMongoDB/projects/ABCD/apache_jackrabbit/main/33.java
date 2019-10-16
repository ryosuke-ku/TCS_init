    public boolean hasNode(String relPath) throws RepositoryException {
        // check state of this instance
        sanityCheck();

        NodeId id = resolveRelativeNodePath(relPath);
        if (id != null) {
            return itemMgr.itemExists(id);
        } else {
            return false;
        }
    }
