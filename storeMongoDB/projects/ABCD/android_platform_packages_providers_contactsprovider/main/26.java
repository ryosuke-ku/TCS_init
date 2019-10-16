    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder, CancellationSignal cancellationSignal) {
        if (VERBOSE_LOGGING) {
            Log.v(TAG, "query: uri=" + uri + "  projection=" + Arrays.toString(projection) +
                    "  selection=[" + selection + "]  args=" + Arrays.toString(selectionArgs) +
                    "  order=[" + sortOrder + "] CPID=" + Binder.getCallingPid() +
                    " User=" + UserUtils.getCurrentUserHandle(getContext()));
        }
        waitForAccess(mReadAccessLatch);

        if (!isDirectoryParamValid(uri)) {
            return null;
        }

        // Check enterprise policy if caller does not come from same profile
        if (!(isCallerFromSameUser() || mEnterprisePolicyGuard.isCrossProfileAllowed(uri))) {
            return createEmptyCursor(uri, projection);
        }
        // Query the profile DB if appropriate.
        if (mapsToProfileDb(uri)) {
            switchToProfileMode();
            return mProfileProvider.query(uri, projection, selection, selectionArgs, sortOrder,
                    cancellationSignal);
        }
        incrementStats(mQueryStats);

        // Otherwise proceed with a normal query against the contacts DB.
        switchToContactMode();

        return queryDirectoryIfNecessary(uri, projection, selection, selectionArgs, sortOrder,
                cancellationSignal);
    }
