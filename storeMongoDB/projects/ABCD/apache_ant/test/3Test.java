    public void testAddTaskDefinition() {
        p.addBuildListener(mbl);

        p.addTaskDefinition("Ok", DummyTaskOk.class);
        assertEquals(DummyTaskOk.class, p.getTaskDefinitions().get("Ok"));
        p.addTaskDefinition("OkNonTask", DummyTaskOkNonTask.class);
        assertEquals(DummyTaskOkNonTask.class, p.getTaskDefinitions().get("OkNonTask"));
        mbl.assertEmpty();

        assertTaskDefFails(DummyTaskPrivate.class,   DummyTaskPrivate.class   + " is not public");

        assertTaskDefFails(DummyTaskProtected.class,
                           DummyTaskProtected.class + " is not public");

        assertTaskDefFails(DummyTaskPackage.class,   DummyTaskPackage.class   + " is not public");

        assertTaskDefFails(DummyTaskAbstract.class,  DummyTaskAbstract.class  + " is abstract");
        assertTaskDefFails(DummyTaskInterface.class, DummyTaskInterface.class + " is abstract");

        assertTaskDefFails(DummyTaskWithoutDefaultConstructor.class, "No public no-arg constructor in " + DummyTaskWithoutDefaultConstructor.class);
        assertTaskDefFails(DummyTaskWithoutPublicConstructor.class,  "No public no-arg constructor in " + DummyTaskWithoutPublicConstructor.class);

        assertTaskDefFails(DummyTaskWithoutExecute.class,       "No public execute() in " + DummyTaskWithoutExecute.class);
        assertTaskDefFails(DummyTaskWithNonPublicExecute.class, "No public execute() in " + DummyTaskWithNonPublicExecute.class);

        mbl.addBuildEvent("return type of execute() should be void but was \"int\" in " + DummyTaskWithNonVoidExecute.class, Project.MSG_WARN);
        p.addTaskDefinition("NonVoidExecute", DummyTaskWithNonVoidExecute.class);
        mbl.assertEmpty();
        assertEquals(DummyTaskWithNonVoidExecute.class, p.getTaskDefinitions().get("NonVoidExecute"));
    }
