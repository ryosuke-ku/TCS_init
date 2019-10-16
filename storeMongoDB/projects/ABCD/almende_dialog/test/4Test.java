    public void attachIngAnAdapterAddAndRemoveATimestampTest() throws Exception {
        
        String accountId1 = new UUID().toString();

        AdapterConfig adapter = createAdapterConfig(AdapterType.CALL.toString(), null, null, localFullAddressBroadsoft,
            null);
        Assert.assertThat(adapter, Matchers.notNullValue());
        //attach the adapter
        AdapterAgent adapterAgent = new AdapterAgent();
        adapterAgent.addAccount(adapter.getConfigId(), accountId1);

        AdapterConfig adapterConfig = AdapterConfig.getAdapterConfig(adapter.getConfigId());
        //assert that the timestamp is added
        Long attachTimestamp = adapterConfig.getAttachTimestamp(accountId1);
        Assert.assertThat(attachTimestamp, Matchers.notNullValue());
        Long detachTimestamp = adapterConfig.getDetachTimestamp( accountId1);
        Assert.assertNull( detachTimestamp );
        
        // remove the adapter and 
        adapterAgent.removeAdapter( adapter.getConfigId(), accountId1 );
        adapterConfig = AdapterConfig.getAdapterConfig(adapter.getConfigId());
        
        // check if the attach timestamp is still there.
        Assert.assertEquals( attachTimestamp, adapterConfig.getAttachTimestamp(accountId1) );
        // check if the detach timestamp is set
        detachTimestamp = adapterConfig.getDetachTimestamp( accountId1);
        Assert.assertThat(detachTimestamp, Matchers.greaterThanOrEqualTo(attachTimestamp));
        
        // add the adapter again
        adapterAgent.addAccount(adapter.getConfigId(), accountId1);
        adapterConfig = AdapterConfig.getAdapterConfig(adapter.getConfigId());
        
        //check if the timestamp has increased
        long attachTimestamp2 = adapterConfig.getAttachTimestamp(accountId1);
        Assert.assertThat(attachTimestamp2, Matchers.greaterThan(attachTimestamp));
    }
