    public void testTestInvalidDirectory() throws Exception {
        final ContactsProvider2 provider = (ContactsProvider2) getProvider();
        assertTrue(provider.isDirectoryParamValid(Contacts.CONTENT_FILTER_URI));
        assertFalse(provider.isDirectoryParamValid(buildContactsFilterUriWithDirectory("")));
        assertTrue(provider.isDirectoryParamValid(buildContactsFilterUriWithDirectory("0")));
        assertTrue(provider.isDirectoryParamValid(buildContactsFilterUriWithDirectory("123")));
        assertFalse(provider.isDirectoryParamValid(buildContactsFilterUriWithDirectory("abc")));
    }
