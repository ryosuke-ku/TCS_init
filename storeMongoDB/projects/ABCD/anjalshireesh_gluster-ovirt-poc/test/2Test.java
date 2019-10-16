    public void runSingleCommand() {
        roles role = new roles();
        role.setId(Guid.NewGuid());
        role.setname("Random_" + new Random().nextInt());
        role.setType(RoleType.USER);
        ArrayList<ActionGroup> groups = new ArrayList<ActionGroup>();
        groups.add(ActionGroup.CHANGE_VM_CD);
        VdcReturnValueBase value = backend.runInternalAction(VdcActionType.AddRoleWithActionGroups,
                new RoleWithActionGroupsParameters(role, groups));
        assertTrue(value.getSucceeded());
        System.out.println(value);
    }
