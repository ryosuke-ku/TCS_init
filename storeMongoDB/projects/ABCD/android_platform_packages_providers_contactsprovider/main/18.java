    private int updateData(ContentValues values, Cursor c, boolean callerIsSyncAdapter,
            boolean callerIsMetadataSyncAdapter) {
        if (values.size() == 0) {
            return 0;
        }

        final SQLiteDatabase db = mDbHelper.get().getWritableDatabase();

        final String mimeType = c.getString(DataRowHandler.DataUpdateQuery.MIMETYPE);
        if (Phone.CONTENT_ITEM_TYPE.equals(mimeType)) {
            maybeTrimLongPhoneNumber(values);
        }

        DataRowHandler rowHandler = getDataRowHandler(mimeType);
        boolean updated =
                rowHandler.update(db, mTransactionContext.get(), values, c,
                        callerIsSyncAdapter, callerIsMetadataSyncAdapter);
        if (Photo.CONTENT_ITEM_TYPE.equals(mimeType)) {
            scheduleBackgroundTask(BACKGROUND_TASK_CLEANUP_PHOTOS);
        }
        return updated ? 1 : 0;
    }
