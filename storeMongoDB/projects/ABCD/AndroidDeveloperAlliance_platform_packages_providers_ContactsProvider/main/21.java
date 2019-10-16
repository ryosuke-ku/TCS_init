    private Cursor query(final SQLiteDatabase db, SQLiteQueryBuilder qb, String[] projection,
            String selection, String[] selectionArgs, String sortOrder, String groupBy,
            String having, String limit, CancellationSignal cancellationSignal) {
        if (projection != null && projection.length == 1
                && BaseColumns._COUNT.equals(projection[0])) {
            qb.setProjectionMap(sCountProjectionMap);
        }
        final Cursor c = qb.query(db, projection, selection, selectionArgs, groupBy, having,
                sortOrder, limit, cancellationSignal);
        if (c != null) {
            c.setNotificationUri(getContext().getContentResolver(), ContactsContract.AUTHORITY_URI);
        }
        return c;
    }
