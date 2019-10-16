    public void smsAdapterProviderMatchTest() throws Exception {

        AdapterConfig adapter = createAdapterConfig(AdapterType.SMS.getName(), AdapterProviders.CM, TEST_ACCOUNT_ID,
                                                    localAddressBroadsoft, localFullAddressBroadsoft, null);
        assertTrue(adapter.isSMSAdapter());
        assertTrue(AdapterProviders.isSMSAdapter(adapter.getProvider().toString()));
    }
