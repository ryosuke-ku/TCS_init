    public Set<Entry<Long, Object>> getUpdatedSyncStates() {
        if (mUpdatedSyncStates == null) mUpdatedSyncStates = Maps.newHashMap();
        return mUpdatedSyncStates.entrySet();
    }
