    public void changeCursor(int partition, Cursor cursor) {
        Cursor prevCursor = mPartitions[partition].cursor;
        if (prevCursor != cursor) {
            if (prevCursor != null && !prevCursor.isClosed()) {
                prevCursor.close();
            }
            mPartitions[partition].cursor = cursor;
            if (cursor != null) {
                mPartitions[partition].idColumnIndex = cursor.getColumnIndex("_id");
            }
            invalidate();
            notifyDataSetChanged();
        }
    }
