    public void testOneSystemOneUserOneDeleted() throws Exception {
        install(getCa1(), getAliasSystemCa1());
        store.installCertificate(getCa2());
        store.deleteCertificateEntry(getAliasSystemCa1());
        assertDeleted(getCa1(), getAliasSystemCa1());
        assertRootCa(getCa2(), getAliasUserCa2());
        assertAliases(getAliasUserCa2());
    }
