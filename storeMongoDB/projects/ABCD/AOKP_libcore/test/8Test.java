    private void assertDeleted(X509Certificate x, String alias) {
        assertNull(store.getCertificate(alias));
        assertFalse(store.containsAlias(alias));
        assertNull(store.getCertificateAlias(x));
        assertFalse(store.isTrustAnchor(x));
        assertEquals(store.allSystemAliases().contains(alias),
                     store.getCertificate(alias, true) != null);
    }
