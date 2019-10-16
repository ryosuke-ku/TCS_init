    /* package */ int updateDataUsageStat(
            List<Long> dataIds, String type, long currentTimeMillis) {

        final SQLiteDatabase db = mActiveDb.get();

        final String typeString = String.valueOf(getDataUsageFeedbackType(type, null));
        final String currentTimeMillisString = String.valueOf(currentTimeMillis);

        for (long dataId : dataIds) {
            final String dataIdString = String.valueOf(dataId);
            mSelectionArgs2[0] = dataIdString;
            mSelectionArgs2[1] = typeString;
            final Cursor cursor = db.query(DataUsageStatQuery.TABLE,
                    DataUsageStatQuery.COLUMNS, DataUsageStatQuery.SELECTION,
                    mSelectionArgs2, null, null, null);
            try {
                if (cursor.moveToFirst()) {
                    final long id = cursor.getLong(DataUsageStatQuery.ID);

                    mSelectionArgs2[0] = currentTimeMillisString;
                    mSelectionArgs2[1] = String.valueOf(id);

                    db.execSQL("UPDATE " + Tables.DATA_USAGE_STAT +
                            " SET " + DataUsageStatColumns.TIMES_USED + "=" +
                                "ifnull(" + DataUsageStatColumns.TIMES_USED +",0)+1" +
                            "," + DataUsageStatColumns.LAST_TIME_USED + "=?" +
                            " WHERE " + DataUsageStatColumns._ID + "=?",
                            mSelectionArgs2);
                } else {
                    mSelectionArgs4[0] = dataIdString;
                    mSelectionArgs4[1] = typeString;
                    mSelectionArgs4[2] = "1"; // times used
                    mSelectionArgs4[3] = currentTimeMillisString;
                    db.execSQL("INSERT INTO " + Tables.DATA_USAGE_STAT +
                            "(" + DataUsageStatColumns.DATA_ID +
                            "," + DataUsageStatColumns.USAGE_TYPE_INT +
                            "," + DataUsageStatColumns.TIMES_USED +
                            "," + DataUsageStatColumns.LAST_TIME_USED +
                            ") VALUES (?,?,?,?)",
                            mSelectionArgs4);
                }
            } finally {
                cursor.close();
            }
        }

        return dataIds.size();
    }
