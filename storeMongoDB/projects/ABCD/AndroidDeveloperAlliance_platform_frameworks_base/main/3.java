    public int delete(Uri url, String where, String[] whereArgs) {
        SqlArguments args = new SqlArguments(url, where, whereArgs);
        if (TABLE_FAVORITES.equals(args.table)) {
            return 0;
        } else if (TABLE_OLD_FAVORITES.equals(args.table)) {
            args.table = TABLE_FAVORITES;
        }
        checkWritePermissions(args);

        sKnownMutationsInFlight.incrementAndGet();
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = db.delete(args.table, args.where, args.args);
        sKnownMutationsInFlight.decrementAndGet();
        if (count > 0) {
            SettingsCache.invalidate(args.table);  // before we notify
            sendNotify(url);
        }
        startAsyncCachePopulation();
        if (LOCAL_LOGV) Log.v(TAG, args.table + ": " + count + " row(s) deleted");
        return count;
    }
