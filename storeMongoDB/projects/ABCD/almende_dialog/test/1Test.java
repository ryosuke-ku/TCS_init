    public void subscriptionDDRsAreCreatedEverySecondTest() throws Exception {

        DateTime serverCurrentTime = TimeUtils.getServerCurrentTime();
        //create an adapter
        getTestDDRPrice(DDRTypeCategory.ADAPTER_PURCHASE, 0.5, "Test", UnitType.PART, null, null);
        String adapterId = adapterAgent.createMBAdapter("TEST", null, "", "", null, TEST_ACCOUNT_ID, null, null);
        AdapterConfig adapterConfig = AdapterConfig.getAdapterConfig(adapterId);
        //create a new price
        getTestDDRPrice(DDRTypeCategory.SUBSCRIPTION_COST, 0.5, "Test", UnitType.SECOND,
                        AdapterType.getByValue(adapterConfig.getAdapterType()), adapterConfig.getConfigId());
        //check if the adapter is charged a subscription fee
        DDRRecord ddrForSubscription1stSecond = DDRUtils.createDDRForSubscription(adapterConfig, false);
        Thread.sleep(1000); //sleep for a second. ugly but works
        DDRRecord ddrForSubscriptionFor2ndSecond = DDRUtils.createDDRForSubscription(adapterConfig, false);
        assertThat(ddrForSubscription1stSecond.getStart(),
                   Matchers.greaterThan(serverCurrentTime.minusSeconds(1).getMillis()));
        assertThat(ddrForSubscriptionFor2ndSecond.getStart(),
                   Matchers.greaterThan(ddrForSubscription1stSecond.getStart()));
        //assert three ddrs are created. 1 for adapter creation. 2 for suscriptions
        Collection<DDRRecord> allDdrRecords = getDDRRecordsByAccountId( TEST_ACCOUNT_ID );
        assertThat(allDdrRecords.size(), Matchers.is(3));
    }
