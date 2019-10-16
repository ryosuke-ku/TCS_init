    public Set<Long> getDirtyRawContactIds() {
        if (mDirtyRawContacts == null) mDirtyRawContacts = Sets.newHashSet();
        return mDirtyRawContacts;
    }
