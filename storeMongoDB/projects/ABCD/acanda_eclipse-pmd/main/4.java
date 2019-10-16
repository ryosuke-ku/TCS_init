    public void setWorkspaceTypeSelected(final boolean isWorkspaceTypeSelected) {
        setProperty(WORKSPACE_TYPE_SELECTED, this.isWorkspaceTypeSelected, this.isWorkspaceTypeSelected = isWorkspaceTypeSelected);
        updateBrowseEnabled();
    }
