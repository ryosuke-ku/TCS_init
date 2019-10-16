    public void fetchLargeDDRRecordByAdapterType() throws Exception {

        int ddrCount = 1000;
        String secondAddress = "0612345678";
        AdapterConfig adapterConfig = createBroadsoftAdapter();
        HashMap<String, Session> sessionKeyMap = new HashMap<String, Session>(1);
        sessionKeyMap.put(remoteAddressVoice, createSession(adapterConfig, remoteAddressVoice));
        sessionKeyMap.put(secondAddress, createSession(adapterConfig, secondAddress));

        new DDRRecordAgent().generateDefaultDDRTypes();
        createTestDDRPrice(DDRTypeCategory.OUTGOING_COMMUNICATION_COST, 0.1, "test", UnitType.MINUTE, AdapterType.CALL,
            null);
        HashMap<String, String> toAddress = new HashMap<String, String>();
        toAddress.put(remoteAddressVoice, null);
        toAddress.put(secondAddress, null);
        //create a 1000 ddrRecords
        int count = 0;
        while (count++ < ddrCount) {
            DDRUtils.createDDRRecordOnOutgoingCommunication(adapterConfig, TEST_ACCOUNT_ID, toAddress,
                "some test message", sessionKeyMap);
        }
        //fetch the ddrRecords
        List<DDRRecord> allDdrRecords = DDRRecord.getDDRRecords(TEST_ACCOUNT_ID, Arrays.asList(AdapterType.CALL), null,
            null, null, null, null, null, null, null, null, null, null, null);
        assertThat(allDdrRecords.size(), Matchers.is(ddrCount));

        //update couple of adapters and flush their adapterType
        DDRRecord ddrRecord = allDdrRecords.get(0);
        ddrRecord.setAdapterType(null);
        ddrRecord.createOrUpdate();
        ddrRecord = allDdrRecords.get(ddrCount - 1);
        ddrRecord.setAdapterType(null);
        ddrRecord.createOrUpdate();
        allDdrRecords = DDRRecord.getDDRRecords(TEST_ACCOUNT_ID, Arrays.asList(AdapterType.CALL), null, null, null,
            null, null, null, null, null, null, null, null, null);
        assertThat(allDdrRecords.size(), Matchers.is(ddrCount -2));
    }
