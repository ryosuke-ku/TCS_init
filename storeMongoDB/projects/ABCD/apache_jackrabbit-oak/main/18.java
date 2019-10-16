    protected boolean isExpired(@Nonnull Authorizable auth, long expirationTime, @Nonnull String type) throws RepositoryException {
        Value[] values = auth.getProperty(REP_LAST_SYNCED);
        if (values == null || values.length == 0) {
            if (log.isDebugEnabled()) {
                log.debug("{} of {} '{}' need sync. " + REP_LAST_SYNCED + " not set.",
                        type, auth.isGroup() ? "group" : "user", auth.getID());
            }
            return true;
        } else if (now - values[0].getLong() > expirationTime) {
            if (log.isDebugEnabled()) {
                log.debug("{} of {} '{}' need sync. " + REP_LAST_SYNCED + " expired ({} > {})",
                        type, auth.isGroup() ? "group" : "user", auth.getID(), now - values[0].getLong(), expirationTime);
            }
            return true;
        } else {
            if (log.isDebugEnabled()) {
                log.debug("{} of {} '{}' do not need sync.", type, auth.isGroup() ? "group" : "user", auth.getID());
            }
            return false;
        }
    }
