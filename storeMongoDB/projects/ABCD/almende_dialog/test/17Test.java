    public void adapterFetchBySharedAccountsTest() throws Exception {

        String linkedAccountId = UUID.randomUUID().toString();
        createAdapterWithOwnerAndSharedAccount(UUID.randomUUID().toString(), Arrays.asList(linkedAccountId));
        ArrayList<AdapterConfig> allAdapters = AdapterConfig.findAdapterByAccount(linkedAccountId);
        assertTrue(allAdapters.size() == 1);
        assertTrue(allAdapters.iterator().next().getAccounts().contains(linkedAccountId));
    }
