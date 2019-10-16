    public void runSingleQuery() {
        MultilevelAdministrationsQueriesParameters parameters = new MultilevelAdministrationsQueriesParameters();
        VdcQueryReturnValue value = backend.runInternalQuery(VdcQueryType.GetAllRoles, parameters);
        assertTrue(value.getSucceeded());
        Collection<roles> roles = (Collection<roles>) value.getReturnValue();
        for (roles role : roles) {
            System.out.println(role.getname());
        }
    }
