    public void addTaskDefinition(final String taskName, final Class<?> taskClass)
         throws BuildException {
        ComponentHelper.getComponentHelper(this).addTaskDefinition(taskName,
                taskClass);
    }
