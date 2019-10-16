    public boolean isCheckedOut() throws RepositoryException {
        // check state of this instance
        sanityCheck();

        // try shortcut first:
        // if current node is 'new' we can safely consider it checked-out since
        // otherwise it would had been impossible to add it in the first place
        if (isNew()) {
            return true;
        }

        // search nearest ancestor that is versionable
        // FIXME should not only rely on existence of jcr:isCheckedOut property
        // but also verify that node.isNodeType("mix:versionable")==true;
        // this would have a negative impact on performance though...
        try {
            NodeState state = getNodeState();
            while (!state.hasPropertyName(JCR_ISCHECKEDOUT)) {
                ItemId parentId = state.getParentId();
                if (parentId == null) {
                    // root reached or out of hierarchy
                    return true;
                }
                state = (NodeState)
                    sessionContext.getItemStateManager().getItemState(parentId);
            }
            PropertyId id = new PropertyId(state.getNodeId(), JCR_ISCHECKEDOUT);
            PropertyState ps =
                (PropertyState) sessionContext.getItemStateManager().getItemState(id);
            InternalValue[] values = ps.getValues();
            if (values == null || values.length != 1) {
                // the property is not fully set, or it is a multi-valued property
                // in which case it's probably not mix:versionable
                return true;
            }
            return values[0].getBoolean();
        } catch (ItemStateException e) {
            throw new RepositoryException(e);
        }
    }
