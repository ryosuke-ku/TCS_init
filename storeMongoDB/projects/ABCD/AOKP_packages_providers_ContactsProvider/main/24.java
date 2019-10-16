    public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
        boolean success = false;
        try {
            if (mode.equals("r")) {
                waitForAccess(mReadAccessLatch);
            } else {
                waitForAccess(mWriteAccessLatch);
            }
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
                Log.v(TAG, "openAssetFile uri=" + uri + " mode=" + mode + " success=" + success);
            }
        }
    }
