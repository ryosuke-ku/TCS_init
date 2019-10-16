    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        ZNRecord record = null;
        if (bytes != null) {
            Gson gson = new Gson();
            record = gson.fromJson(new String(bytes), ZNRecord.class);
        }
        return record;
    }
