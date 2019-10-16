    private void assertRootCa(X509Certificate x, String alias) {
        assertIntermediateCa(x, alias);
        assertEquals(x, store.findIssuer(x));
    }
