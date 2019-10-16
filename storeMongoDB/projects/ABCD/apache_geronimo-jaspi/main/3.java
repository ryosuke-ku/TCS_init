    private String registerConfigProvider(AuthConfigProvider provider, String layer, String appContext, String description, boolean persistent, Map<String, String> constructorParam, String className) {
        String key = ConfigProviderType.getRegistrationKey(layer, appContext);
        // Get or create context
        ConfigProviderInfo info = getRegistrations().get(key);
        List<RegistrationListener> listeners;
        if (info == null) {
            listeners = new ArrayList<RegistrationListener>();
        } else {
            if (persistent != info.isPersistent()) {
                throw new IllegalArgumentException("Cannot change the persistence state");
            }
            listeners = info.getListeners();
        }
        // Create provider
        ConfigProviderType ctx = new ConfigProviderType(layer, appContext, persistent, persistent? null: this);
        ctx.setDescription(description);
        if (persistent) {
            if (provider != null) {
                throw new IllegalStateException("Config provider supplied but should be created");
            }
            ctx.setClassName(className);
            ctx.setProperties(constructorParam);
            provider = ConfigProviderImpl.newConfigProvider(this, ctx);
        } else {
            if (provider == null) {
                throw new IllegalStateException("No config provider to set");
            }
        }
        info = new ConfigProviderInfo(provider, ctx, listeners, persistent);
        getRegistrations().put(key, info);

        // Notify listeners
        for (RegistrationListener listener : listeners) {
            listener.notify(info.getMessageLayer(), info.getAppContext());
        }
        // Return registration Id
        return key;
    }
