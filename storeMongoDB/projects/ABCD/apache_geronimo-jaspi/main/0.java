    public synchronized AuthConfigProvider getConfigProvider(String layer, String appContext, RegistrationListener listener) {
        if (layer == null) {
            throw new NullPointerException("messageLayer");
        }
        if (appContext == null) {
            throw new NullPointerException("appContext");
        }
        ConfigProviderInfo ctx = getRegistrations().get(ConfigProviderType.getRegistrationKey(layer, appContext));
        if (ctx == null) {
            ctx = getRegistrations().get(ConfigProviderType.getRegistrationKey(null, appContext));
        }
        if (ctx == null) {
            ctx = getRegistrations().get(ConfigProviderType.getRegistrationKey(layer, null));
        }
        if (ctx == null) {
            ctx = getRegistrations().get(ConfigProviderType.getRegistrationKey(null, null));
        }
        if (ctx != null) {
            if (listener != null) {
                ctx.getListeners().add(listener);
            }
            return ctx.getAuthConfigProvider();
        }
        return null;
    }
