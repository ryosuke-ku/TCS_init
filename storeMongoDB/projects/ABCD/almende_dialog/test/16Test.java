    public void adapterWithNoOwnerFetchBySharedAccountTest() throws Exception {

        String accountId = UUID.randomUUID().toString();
        //create adapters
        createAdapterWithOwnerAndSharedAccount(null, Arrays.asList(accountId));
        
        //assert that fetch by linked accounts fetches 1 adapters
        ArrayList<AdapterConfig> allAdapters = AdapterConfig.findAdapterByAccount(accountId, null, null);
        assertTrue(allAdapters.size() == 1);
        assertTrue(allAdapters.iterator().next().getAccounts().contains(accountId));
        
        //assert that fetch by linked accounts fetches adapters
        allAdapters = AdapterConfig.findAdapterByOwner(accountId, null, null);
        assertTrue(allAdapters.size() == 1);
        assertTrue(allAdapters.iterator().next().getOwner().equals(accountId));
    }
