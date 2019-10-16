    public void validateWorkspaceConfigurationWithProjectOutsideWorkspace() throws IOException {
        final Path ruleSetFile = createRuleSetFile();
        
        final IProject project = mock(IProject.class);
        final IWorkspace workspace = mock(IWorkspace.class);
        final IWorkspaceRoot root = mock(IWorkspaceRoot.class);
        when(project.getWorkspace()).thenReturn(workspace);
        when(workspace.getRoot()).thenReturn(root);
        when(root.getProject(anyString())).thenReturn(project);
        when(project.getLocationURI()).thenReturn(ruleSetFile.getParent().toUri());
        
        final AddRuleSetConfigurationModel model = new AddRuleSetConfigurationModel(project);
        model.setWorkspaceTypeSelected(true);
        model.setName("X");
        model.setLocation("ProjectX/" + ruleSetFile.getName(ruleSetFile.getNameCount() - 1));
        final ValidationResult validationResult = new ValidationResult();
        
        model.validate(AddRuleSetConfigurationModel.LOCATION, validationResult);
        
        if (validationResult.hasErrors()) {
            final String msg = "The validation should not result in any errors "
                    + "if the project is located outside the workspace. First error: ";
            fail(msg + validationResult.getFirstErrorMessage());
        }
    }
