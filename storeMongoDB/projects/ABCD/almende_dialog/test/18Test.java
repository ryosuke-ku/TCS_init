    private AdapterConfig createAdapterWithOwnerAndSharedAccount(String ownerId, Collection<String> accountIds)
        throws Exception {

        AdapterConfig adapter = createAdapterConfig(AdapterType.CALL.getName(), ownerId, accountIds,
                                                    localAddressBroadsoft, null);
        if (accountIds != null) {
            adapter.setAccounts(accountIds);
        }
        adapter.update();
        return adapter;
    }
