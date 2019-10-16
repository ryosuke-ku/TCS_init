    public void removeAdapter(@Name("adapterId") String adapterId, @Name("accountId") String accountId)
        throws Exception {

        AdapterConfig config = AdapterConfig.getAdapterConfig(adapterId);
        if (config == null) {
            throw new Exception("No adapter with this id owned by you");
        }
        else {
            AdapterType adapterType = AdapterType.fromJson(config.getAdapterType());
            //if the accoutnId is the owner of the adapter requesting a remove operation
            //if the adapter is a private adapter, just delete it from the system
            if (AdapterConfig.checkIfAdapterMatchesForAccountId(Arrays.asList(accountId), config, true) != null &&
                config.isPrivate()) {

                config.delete();
            }
            //if its a shared adapter provided by ASK-Fast.
            else if (AdapterConfig.checkIfAdapterMatchesForAccountId(Arrays.asList(accountId), config, false) != null) {
                //if the accountId is the owner, mark adapter as inactive
                config.removeAccount(accountId);
                config.getProperties().remove(AdapterConfig.DIALOG_ID_KEY);
                switch (adapterType) {
                    case XMPP:
                        //deregister if its an askfast xmpp account
                        if (config.getMyAddress().contains("xmpp.ask-fast.com")) {
                            deregisterASKFastXMPPAdapter(config.getMyAddress(), accountId, adapterId);
                        }
                    default:
                        config.update();
                        break;
                }
            }
        }
    }
