    public void canDetachInactiveDomain() {
        storageDomainIsInactive();
        storagePoolExists();
        masterDomainIsUp();
        isNotLocalData();
        canDetachDomain();
        assertTrue(cmd.canDetachDomain(false, false, false));
    }
