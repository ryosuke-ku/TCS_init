    public Node getNode(final String relPath) throws RepositoryException {
        return perform(new SessionOperation<Node>() {
            public Node perform(SessionContext context)
                    throws RepositoryException {
                Path p = resolveRelativePath(relPath);
                NodeId id = getNodeId(p);
                if (id == null) {
                    throw new PathNotFoundException(relPath);
                }

                // determine parent as mandated by path
                NodeId parentId = null;
                if (!p.denotesRoot()) {
                    parentId = getNodeId(p.getAncestor(1));
                }
                try {
                    // if the node is shareable, it now returns the node
                    // with the right parent
                    if (parentId != null) {
                        return itemMgr.getNode(id, parentId);
                    } else {
                        return (NodeImpl) itemMgr.getItem(id);
                    }
                } catch (AccessDeniedException e) {
                    throw new PathNotFoundException(relPath);
                } catch (ItemNotFoundException e) {
                    throw new PathNotFoundException(relPath);
                }
            }
            public String toString() {
                return "node.getNode(" + relPath + ")";
            }
        });
    }
