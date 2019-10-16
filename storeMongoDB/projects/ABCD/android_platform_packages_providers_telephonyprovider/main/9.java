    void putMmsMessagesToProvider(JsonReader jsonReader) throws IOException {
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            final Mms mms = readMmsFromReader(jsonReader);
            if (doesMmsExist(mms)) {
                if (DEBUG) {
                    Log.e(TAG, String.format("Mms: %s already exists", mms.toString()));
                }
                continue;
            }
            addMmsMessage(mms);
        }
    }
