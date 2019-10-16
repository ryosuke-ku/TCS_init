    public String getName() throws RepositoryException {
        return perform(new SessionOperation<String>() {
            public String perform(SessionContext context)
                    throws RepositoryException {
                NodeId parentId = data.getNodeState().getParentId();
                if (parentId == null) {
                    return ""; // this is the root node
                }

                Name name;
                if (!isShareable()) {
                    name = context.getHierarchyManager().getName(id);
                } else {
                    name = context.getHierarchyManager().getName(
                            getNodeId(), parentId);
                }
                return context.getJCRName(name);
            }
            public String toString() {
                return "node.getName()";
            }
        });
    }
