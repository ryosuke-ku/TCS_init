    public void setAuthenticationProvider(Class<? extends AuthenticationProvider> authenticationProviderClass) {
        if (authenticationProviderClass == null) {
            remove(SessionParameter.AUTHENTICATION_PROVIDER_CLASS);
        } else {
            put(SessionParameter.AUTHENTICATION_PROVIDER_CLASS, authenticationProviderClass.getName());
        }
    }
