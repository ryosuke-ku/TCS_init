    public void broadsoftInMediaPropertiesMatchTest() throws Exception {

        AdapterConfig adapter = createAdapterConfig(AdapterType.CALL.getName(), AdapterProviders.BROADSOFT,
                                                    TEST_ACCOUNT_ID, localAddressBroadsoft, localFullAddressBroadsoft,
                                                    "");
        assertTrue(AdapterProviders.isCallAdapter(adapter.getProvider().toString()));
        adapter.setAdapterType(AdapterType.CALL.toString());
        adapter.update();
        assertTrue(adapter.isCallAdapter());
    }
