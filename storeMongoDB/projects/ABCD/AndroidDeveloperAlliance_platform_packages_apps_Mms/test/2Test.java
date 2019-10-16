    private Uri storeMessage(Context context, String address, String message) {
        // Store the message in the content provider.
        ContentValues values = new ContentValues();
//        values.put(Sms.ERROR_CODE, 0);
        values.put(Inbox.ADDRESS, address);

        // Use now for the timestamp to avoid confusion with clock
        // drift between the handset and the SMSC.
        values.put(Inbox.DATE, new Long(System.currentTimeMillis()));
        values.put(Inbox.PROTOCOL, 0);
        values.put(Inbox.READ, Integer.valueOf(0));
//        if (sms.getPseudoSubject().length() > 0) {
//            values.put(Inbox.SUBJECT, sms.getPseudoSubject());
//        }
        values.put(Inbox.REPLY_PATH_PRESENT, 0);
        values.put(Inbox.SERVICE_CENTER, 0);
        values.put(Inbox.BODY, message);

        // Make sure we've got a thread id so after the insert we'll be able to delete
        // excess messages.
        Long threadId = 0L;
        Contact cacheContact = Contact.get(address,true);
        if (cacheContact != null) {
            address = cacheContact.getNumber();
        }

        if (((threadId == null) || (threadId == 0)) && (address != null)) {
            values.put(Sms.THREAD_ID, Threads.getOrCreateThreadId(
                               context, address));
        }

        ContentResolver resolver = context.getContentResolver();

        Uri insertedUri = SqliteWrapper.insert(context, resolver, Inbox.CONTENT_URI, values);

        // Now make sure we're not over the limit in stored messages
        threadId = values.getAsLong(Sms.THREAD_ID);
        Recycler.getSmsRecycler().deleteOldMessagesByThreadId(context, threadId);

        return insertedUri;
    }
