    public int update(Uri url, ContentValues initialValues, String where, String[] whereArgs) {
        SqlArguments args = new SqlArguments(url, where, whereArgs);
        if (TABLE_FAVORITES.equals(args.table)) {
            return 0;
        }
        checkWritePermissions(args);

        sKnownMutationsInFlight.incrementAndGet();
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = db.update(args.table, initialValues, args.where, args.args);
        sKnownMutationsInFlight.decrementAndGet();
        if (count > 0) {
            SettingsCache.invalidate(args.table);  // before we notify
            sendNotify(url);
        }
        startAsyncCachePopulation();
        if (LOCAL_LOGV) Log.v(TAG, args.table + ": " + count + " row(s) <- " + initialValues);
        return count;
    }
