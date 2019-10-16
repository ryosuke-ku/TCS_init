    public void testIsUserAddedCertificate() throws Exception {
        assertFalse(store.isUserAddedCertificate(getCa1()));
        assertFalse(store.isUserAddedCertificate(getCa2()));
        install(getCa1(), getAliasSystemCa1());
        assertFalse(store.isUserAddedCertificate(getCa1()));
        assertFalse(store.isUserAddedCertificate(getCa2()));
        install(getCa1(), getAliasUserCa1());
        assertTrue(store.isUserAddedCertificate(getCa1()));
        assertFalse(store.isUserAddedCertificate(getCa2()));
        install(getCa2(), getAliasUserCa2());
        assertTrue(store.isUserAddedCertificate(getCa1()));
        assertTrue(store.isUserAddedCertificate(getCa2()));
        store.deleteCertificateEntry(getAliasUserCa1());
        assertFalse(store.isUserAddedCertificate(getCa1()));
        assertTrue(store.isUserAddedCertificate(getCa2()));
        store.deleteCertificateEntry(getAliasUserCa2());
        assertFalse(store.isUserAddedCertificate(getCa1()));
        assertFalse(store.isUserAddedCertificate(getCa2()));
    }
