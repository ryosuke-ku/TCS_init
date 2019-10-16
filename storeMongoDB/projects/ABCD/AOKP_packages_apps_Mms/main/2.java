    public void deleteOldMessagesByThreadId(Context context, long threadId) {
        if (LOCAL_DEBUG) {
            Log.v(TAG, "Recycler.deleteOldMessagesByThreadId this: " + this +
                    " threadId: " + threadId);
        }
        if (!isAutoDeleteEnabled(context)) {
            return;
        }

        deleteMessagesForThread(context, threadId, getMessageLimit(context));
    }
