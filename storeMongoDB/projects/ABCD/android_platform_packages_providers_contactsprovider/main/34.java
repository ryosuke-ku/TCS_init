    public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
        boolean success = false;
        try {
            if (!isDirectoryParamValid(uri)){
                return null;
            }
            if (!isCallerFromSameUser() /* From differnt user */
                    && !mEnterprisePolicyGuard.isCrossProfileAllowed(uri)
                    /* Policy not allowed */){
                return null;
            }
            waitForAccess(mode.equals("r") ? mReadAccessLatch : mWriteAccessLatch);
            final AssetFileDescriptor ret;
            if (mapsToProfileDb(uri)) {
                switchToProfileMode();
                ret = mProfileProvider.openAssetFile(uri, mode);
            } else {
                switchToContactMode();
                ret = openAssetFileLocal(uri, mode);
            }
            success = true;
            return ret;
        } finally {
            if (VERBOSE_LOGGING) {
                Log.v(TAG, "openAssetFile uri=" + uri + " mode=" + mode + " success=" + success +
                        " CPID=" + Binder.getCallingPid() +
                        " User=" + UserUtils.getCurrentUserHandle(getContext()));
            }
        }
    }
