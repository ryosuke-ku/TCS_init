    protected boolean CheckMasterDomainIsUp() {
        boolean returnValue = true;
        List<storage_domains> storageDomains = DbFacade.getInstance().getStorageDomainDAO().getAllForStoragePool(
                getStoragePool().getId());
        storageDomains = LinqUtils.filter(storageDomains, new Predicate<storage_domains>() {
            @Override
            public boolean eval(storage_domains a) {
                return a.getstorage_domain_type() == StorageDomainType.Master
                        && a.getstatus() == StorageDomainStatus.Active;
            }
        });
        if (storageDomains.isEmpty()) {
            addCanDoActionMessage(VdcBllMessages.ACTION_TYPE_FAILED_MASTER_STORAGE_DOMAIN_NOT_ACTIVE);
            returnValue = false;
        }
        return returnValue;
    }
