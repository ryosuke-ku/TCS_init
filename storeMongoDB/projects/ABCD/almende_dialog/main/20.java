    public boolean isSMSAdapter() {

        AdapterType type = AdapterType.getByValue(adapterType);

        //check the adapter type
        if (type != null) {
            if (AdapterType.SMS.equals(type)) {
                return true;
            }
            return false;
        }
        //check if the adapterType has the provider value itself
        else if(AdapterProviders.getByValue(adapterType) != null){
            return AdapterProviders.isSMSAdapter(adapterType);
        }
        //check the properties
        else if(properties != null && properties.get(ADAPTER_PROVIDER_KEY) != null){
            Object provider = properties.get(ADAPTER_PROVIDER_KEY);
            return AdapterProviders.isSMSAdapter(provider.toString());
        }
        return false;
    }
