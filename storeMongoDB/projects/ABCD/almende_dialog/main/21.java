    public AdapterProviders getProvider() {

        AdapterProviders providers = AdapterProviders.getByValue(adapterType);
        //check the adapter type
        if (providers == null && getProperties().get(ADAPTER_PROVIDER_KEY) != null) {
            providers = getProperties(ADAPTER_PROVIDER_KEY, new TypeReference<AdapterProviders>() {
            });
        }
        if (providers == null && AdapterAgent.ADAPTER_TYPE_EMAIL.equalsIgnoreCase(adapterType)) {
            return AdapterProviders.DEFAULT;
        }
        return providers;
    }
