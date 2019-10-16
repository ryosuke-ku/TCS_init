    public boolean hasAnyRole(HierarchicalRoles requested) {
        // 
        if (requested.getNodes().isEmpty()) {
            return true;
        }

        Map<String, Node> requestedNodes = requested.getNodes();

        return matchesAny(nodes, requestedNodes);
    }
