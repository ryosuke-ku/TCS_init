    void putSmsMessagesToProvider(JsonReader jsonReader) throws IOException {
        jsonReader.beginArray();
        int msgCount = 0;
        final int bulkInsertSize = mMaxMsgPerFile;
        ContentValues[] values = new ContentValues[bulkInsertSize];
        while (jsonReader.hasNext()) {
            ContentValues cv = readSmsValuesFromReader(jsonReader);
            if (doesSmsExist(cv)) {
                continue;
            }
            values[(msgCount++) % bulkInsertSize] = cv;
            if (msgCount % bulkInsertSize == 0) {
                mContentResolver.bulkInsert(Telephony.Sms.CONTENT_URI, values);
            }
        }
        if (msgCount % bulkInsertSize > 0) {
            mContentResolver.bulkInsert(Telephony.Sms.CONTENT_URI,
                    Arrays.copyOf(values, msgCount % bulkInsertSize));
        }
        jsonReader.endArray();
    }
