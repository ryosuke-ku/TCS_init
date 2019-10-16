    protected boolean checkStorageDomainStatus(final StorageDomainStatus... statuses) {
        boolean valid = false;
        if(getStorageDomainStatus() != null) {
            valid = Arrays.asList(statuses).contains(getStorageDomainStatus());
        }
        if(!valid) {
            addStorageDomainStatusIllegalMessage();
        }
        return valid;
    }
