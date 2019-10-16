    public synchronized RegistrationContext getRegistrationContext(String registrationID) {
        return getRegistrations().get(registrationID);
    }
