    public void adapterUnlinkSharedPrivateAccountTest() throws Exception {

        Collection<String> linkedAccountIds = Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        String ownerID = UUID.randomUUID().toString();
        //create adapters
        AdapterConfig adapter = createAdapterWithOwnerAndSharedAccount(ownerID, linkedAccountIds);
        adapter.markAsPrivate();
        adapter.update();

        //assert that fetch by linked accounts fetches no adapters
        ArrayList<AdapterConfig> allAdapters = AdapterConfig.findAdapterByAccount(linkedAccountIds.iterator().next(),
                                                                                  null, null);
        assertTrue(allAdapters.size() == 1);
        assertTrue(allAdapters.iterator().next().getAccounts().equals(linkedAccountIds));

        AdapterAgent adapterAgent = new AdapterAgent();
        String removedAccountId = linkedAccountIds.iterator().next();
        adapterAgent.removeAdapter(adapter.getConfigId(), removedAccountId);

        //fetch the adapter again by the removed accountId
        ArrayNode adapterNodes = adapterAgent.getAdapters(removedAccountId, null, null);
        allAdapters = JOM.getInstance().convertValue(adapterNodes, new TypeReference<Collection<AdapterConfig>>() {
        });
        assertEquals(0, allAdapters.size());

        //fetch the adapter again by the owner accountId
        adapterNodes = adapterAgent.getAdapters(ownerID, null, null);
        allAdapters = JOM.getInstance().convertValue(adapterNodes, new TypeReference<Collection<AdapterConfig>>() {
        });
        assertEquals(1, allAdapters.size());
        assertEquals(true, allAdapters.iterator().next().isPrivate());

        //check if the unlinked adapter is still ACTIVE
        adapter = allAdapters.get(0);
        assertEquals(Status.ACTIVE, adapter.getStatus());

        //remove the adapter as the owner
        adapterAgent.removeAdapter(adapter.getConfigId(), ownerID);
        adapterNodes = adapterAgent.getAdapters(ownerID, null, null);
        allAdapters = JOM.getInstance().convertValue(adapterNodes, new TypeReference<Collection<AdapterConfig>>() {
        });
        assertEquals(0, allAdapters.size());
        assertEquals(null, AdapterConfig.getAdapterConfig(adapter.getConfigId()));
    }
