    private void assertCanBeHandled(final Intent intent) {
        PackageManager packageManager = mContext.getPackageManager();
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
        assertNotNull(resolveInfoList);
        // one or more activity can handle this intent.
        assertTrue(resolveInfoList.size() > 0);
    }
