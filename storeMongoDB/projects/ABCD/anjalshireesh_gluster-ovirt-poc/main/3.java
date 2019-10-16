    protected boolean isDetachAllowed(final boolean isRemoveLast) {
        boolean returnValue = true;
        if (getStoragePoolIsoMap() == null) {
            returnValue = false;
            addCanDoActionMessage(VdcBllMessages.STORAGE_DOMAIN_NOT_ATTACHED_TO_STORAGE_POOL);
        } else if (hasImages()) {
            returnValue = false;
            addCanDoActionMessage(VdcBllMessages.ERROR_CANNOT_DETACH_STORAGE_DOMAIN_WITH_IMAGES);
        } else if (!isRemoveLast
                && isMaster()) {

            storage_domains storage_domains =
                    LinqUtils.firstOrNull(DbFacade.getInstance()
                            .getStorageDomainDAO()
                            .getAllForStoragePool(getStorageDomain().getstorage_pool_id().getValue()),
                            new Predicate<storage_domains>() {
                                @Override
                                public boolean eval(storage_domains a) {
                                    return a.getid().equals(getStorageDomain().getid())
                                            && a.getstatus() == StorageDomainStatus.Active;
                                }
                            });
            if (storage_domains == null) {
                returnValue = false;
                addCanDoActionMessage(VdcBllMessages.ERROR_CANNOT_DETACH_LAST_STORAGE_DOMAIN);
            }
        }
        return returnValue;
    }
