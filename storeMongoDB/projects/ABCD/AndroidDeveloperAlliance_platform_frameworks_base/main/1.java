    public Cursor query(Uri url, String[] select, String where, String[] whereArgs, String sort) {
        SqlArguments args = new SqlArguments(url, where, whereArgs);
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        // The favorites table was moved from this provider to a provider inside Home
        // Home still need to query this table to upgrade from pre-cupcake builds
        // However, a cupcake+ build with no data does not contain this table which will
        // cause an exception in the SQL stack. The following line is a special case to
        // let the caller of the query have a chance to recover and avoid the exception
        if (TABLE_FAVORITES.equals(args.table)) {
            return null;
        } else if (TABLE_OLD_FAVORITES.equals(args.table)) {
            args.table = TABLE_FAVORITES;
            Cursor cursor = db.rawQuery("PRAGMA table_info(favorites);", null);
            if (cursor != null) {
                boolean exists = cursor.getCount() > 0;
                cursor.close();
                if (!exists) return null;
            } else {
                return null;
            }
        }

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(args.table);

        Cursor ret = qb.query(db, select, args.where, args.args, null, null, sort);
        ret.setNotificationUri(getContext().getContentResolver(), url);
        return ret;
    }
