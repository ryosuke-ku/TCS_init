    int putMmsMessagesToJson(Cursor cursor,
                             JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginArray();
        int msgCount;
        for (msgCount = 0; msgCount < mMaxMsgPerFile && !cursor.isAfterLast();
                cursor.moveToNext()) {
            msgCount += writeMmsToWriter(jsonWriter, cursor);
        }
        jsonWriter.endArray();
        return msgCount;
    }
