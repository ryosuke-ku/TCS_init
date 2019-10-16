    private AdapterConfig createAdapter(AdapterConfig config, Boolean isPrivate) throws Exception {

        if (AdapterConfig.adapterExists(config.getAdapterType(), config.getMyAddress(), config.getKeyword())) {
            throw new ConflictException("Adapter already exists");
        }
        if (config.getConfigId() == null) {
            config.configId = new UUID().toString();
        }
        //add creation timestamp to the adapter
        config.getProperties().put(AdapterConfig.ADAPTER_CREATION_TIME_KEY, TimeUtils.getServerCurrentTimeInMillis());
        //change the casing to lower in case adatertype if email or xmpp
        if (config.getMyAddress() != null &&
            (AdapterType.EMAIL.equals(AdapterType.getByValue(config.getAdapterType()))) ||
            AdapterType.XMPP.equals(AdapterType.getByValue(config.getAdapterType()))) {

            config.setMyAddress(config.getMyAddress().toLowerCase());
        }
        //check if there is an initialAgent url given. Create a dialog if it is
        if (config.getURLForInboundScenario(null) != null && !config.getURLForInboundScenario(null).isEmpty()) {
            Dialog dialog = Dialog.createDialog("Dialog created on adapter creation",
                                                config.getURLForInboundScenario(null), config.getOwner());
            config.getProperties().put(AdapterConfig.DIALOG_ID_KEY, dialog.getId());
        }
        
        // Check if the status is set, if not try to determine it based on the ownerId
        if (config.getStatus() == null) {        
            if(config.getOwner() == null ) {
                config.setStatus(Status.INACTIVE);
            } else {
                config.setStatus(Status.ACTIVE);
            }
        }

        config.setAdapterType(config.getAdapterType().toLowerCase());
        if (Boolean.TRUE.equals(isPrivate)) {
            config.markAsPrivate();
        }
        TwigCompatibleMongoDatastore datastore = new TwigCompatibleMongoDatastore();
        datastore.store(config);

        if (AdapterProviders.BROADSOFT.equals(config.getProvider())) {
            Broadsoft bs = new Broadsoft(config);
            bs.hideCallerId(config.isAnonymous());
        }
        //add costs for creating this adapter
        DDRRecord ddrRecord = DDRUtils.createDDRRecordOnAdapterPurchase(config, true);
        //push the cost to hte queue
        if (ddrRecord != null) {
            Double totalCost = DDRUtils.calculateDDRCost(ddrRecord);
            DDRUtils.publishDDREntryToQueue(config.getOwner(), totalCost);
            //attach cost to ddr in all cases. Change as on ddr processing taking time
            ddrRecord.setTotalCost(totalCost);
            ddrRecord.createOrUpdate();
        }
        return config;
    }
