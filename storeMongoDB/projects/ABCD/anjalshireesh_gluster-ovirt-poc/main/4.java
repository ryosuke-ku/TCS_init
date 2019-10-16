    protected boolean isNotLocalData(final boolean isInternal) {
        boolean returnValue = true;
        if (this.getStoragePool().getstorage_pool_type() == StorageType.LOCALFS
                && getStorageDomain().getstorage_domain_type() == StorageDomainType.Data
                && !isInternal) {
            returnValue = false;
            addCanDoActionMessage(VdcBllMessages.VDS_GROUP_CANNOT_DETACH_DATA_DOMAIN_FROM_LOCAL_STORAGE);
        }
        return returnValue;
    }
