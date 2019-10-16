    public synchronized boolean removeRegistration(String registrationID) throws SecurityException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new AuthPermission("removeAuthRegistration"));
        }
        ConfigProviderInfo ctx = getRegistrations().remove(registrationID);
        saveConfig();
        if (ctx != null) {
            List<RegistrationListener> listeners = ctx.getListeners();
            for (RegistrationListener listener : listeners) {
                listener.notify(ctx.getMessageLayer(), ctx.getAppContext());
            }
            return true;
        }
        return false;
    }
