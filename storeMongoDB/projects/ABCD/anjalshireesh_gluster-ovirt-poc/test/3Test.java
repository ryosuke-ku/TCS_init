    private void canDetachDomain() {
        doReturn(true).when(cmd).isDetachAllowed(anyBoolean());
    }
