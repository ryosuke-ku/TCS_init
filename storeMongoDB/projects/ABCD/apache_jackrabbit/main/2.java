    public boolean hasEffectiveHold(Path nodePath, boolean checkParent) throws RepositoryException {
        if (!initialized) {
            throw new IllegalStateException("Not initialized.");
        }
        if (holdCnt <= 0) {
            return false;
        }
        PathMap.Element<List<HoldImpl>> element = holdMap.map(nodePath, false);
        List<HoldImpl> holds = element.get();
        if (holds != null) {
            if (element.hasPath(nodePath)) {
                // one or more holds on the specified path
                return true;
            } else if (checkParent && !nodePath.denotesRoot() &&
                    element.hasPath(nodePath.getAncestor(1))) {
                // hold present on the parent node without checking for being
                // a deep hold.
                // this required for removal of a node that can be inhibited
                // by a hold on the node itself, by a hold on the parent or
                // by a deep hold on any ancestor.
                return true;
            } else {
                for (Hold hold : holds) {
                    if (hold.isDeep()) {
                        return true;
                    }
                }
            }
        }
        // no hold at path or no deep hold on parent.
        return false;
    }
