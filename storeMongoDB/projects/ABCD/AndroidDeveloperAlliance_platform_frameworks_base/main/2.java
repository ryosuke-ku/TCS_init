    public Uri insert(Uri url, ContentValues initialValues) {
        SqlArguments args = new SqlArguments(url);
        if (TABLE_FAVORITES.equals(args.table)) {
            return null;
        }
        checkWritePermissions(args);

        // Special case LOCATION_PROVIDERS_ALLOWED.
        // Support enabling/disabling a single provider (using "+" or "-" prefix)
        String name = initialValues.getAsString(Settings.Secure.NAME);
        if (Settings.Secure.LOCATION_PROVIDERS_ALLOWED.equals(name)) {
            if (!parseProviderList(url, initialValues)) return null;
        }

        SettingsCache cache = SettingsCache.forTable(args.table);
        String value = initialValues.getAsString(Settings.NameValueTable.VALUE);
        if (SettingsCache.isRedundantSetValue(cache, name, value)) {
            return Uri.withAppendedPath(url, name);
        }

        sKnownMutationsInFlight.incrementAndGet();
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final long rowId = db.insert(args.table, null, initialValues);
        sKnownMutationsInFlight.decrementAndGet();
        if (rowId <= 0) return null;

        SettingsCache.populate(cache, initialValues);  // before we notify

        if (LOCAL_LOGV) Log.v(TAG, args.table + " <- " + initialValues);
        url = getUriFor(url, initialValues, rowId);
        sendNotify(url);
        return url;
    }
