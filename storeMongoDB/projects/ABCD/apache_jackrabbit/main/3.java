    public boolean hasEffectiveRetention(Path nodePath, boolean checkParent) throws RepositoryException {
        if (!initialized) {
            throw new IllegalStateException("Not initialized.");
        }
        if (retentionCnt <= 0) {
            return false;
        }
        RetentionPolicy rp = null;
        PathMap.Element<RetentionPolicyImpl> element = retentionMap.map(nodePath, true);
        if (element != null) {
            rp = element.get();
        }
        if (rp == null && checkParent && (!nodePath.denotesRoot())) {
            element = retentionMap.map(nodePath.getAncestor(1), true);
            if (element != null) {
                rp = element.get();
            }
        }
        return rp != null;
    }
