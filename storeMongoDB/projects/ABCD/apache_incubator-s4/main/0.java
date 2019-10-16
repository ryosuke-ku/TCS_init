    public byte[] serialize(Object data) throws ZkMarshallingError {
        if (data != null) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(data);
            if (json != null) {
                return json.getBytes();
            }
        }
        return new byte[0];
    }
