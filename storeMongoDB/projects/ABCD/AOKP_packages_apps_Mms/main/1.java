    public void deleteOldMessages(Context context) {
        if (LOCAL_DEBUG) {
            Log.v(TAG, "Recycler.deleteOldMessages this: " + this);
        }
        if (!isAutoDeleteEnabled(context)) {
            return;
        }

        Cursor cursor = getAllThreads(context);
        try {
            int limit = getMessageLimit(context);
            while (cursor.moveToNext()) {
                long threadId = getThreadId(cursor);
                deleteMessagesForThread(context, threadId, limit);
            }
        } finally {
            cursor.close();
        }
    }
