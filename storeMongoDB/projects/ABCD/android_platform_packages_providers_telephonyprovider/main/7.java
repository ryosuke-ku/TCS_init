    int putSmsMessagesToJson(Cursor cursor, JsonWriter jsonWriter) throws IOException {

        jsonWriter.beginArray();
        int msgCount;
        for (msgCount = 0; msgCount < mMaxMsgPerFile && !cursor.isAfterLast();
                ++msgCount, cursor.moveToNext()) {
            writeSmsToWriter(jsonWriter, cursor);
        }
        jsonWriter.endArray();
        return msgCount;
    }
