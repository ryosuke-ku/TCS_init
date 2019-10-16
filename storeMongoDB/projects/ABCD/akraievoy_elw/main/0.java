    protected static ResponseList readRespList(InputStream is) throws IOException {
        JsonFactory jf = new JsonFactory();
        JsonParser jp = jf.createJsonParser(is);
        ResponseList response = new ResponseList();

        if (jp.nextToken() != JsonToken.START_OBJECT) {
            throw new IllegalStateException("expecting start object");
        }
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jp.getCurrentName();
            jp.nextToken(); // move to START_OBJECT
            if ("success".equals(fieldname)) {
                response.setSuccess(Boolean.parseBoolean(jp.getText()));
            }
            if ("message".equals(fieldname)) {
                response.setMessage(jp.getText());    //	LATER this returns 'null' on me!
            }
            if ("data".equals(fieldname)) {
                final Map<String, Map<String, String[]>> dataMap = new TreeMap<String, Map<String, String[]>>();
                while (jp.nextToken() != JsonToken.END_OBJECT) {
                    String dataKeySlot = jp.getCurrentName();
                    jp.nextToken(); // move to START_OBJECT
                    final Map<String, String[]> scopeToIdList = new TreeMap<String, String[]>();
                    while (jp.nextToken() != JsonToken.END_OBJECT) {
                        String dataKeyScope = jp.getCurrentName();
                        jp.nextToken(); // move to START_ARRAY
                        List<String> idList = new ArrayList<String>();
                        while (jp.nextToken() != JsonToken.END_ARRAY) {
                            idList.add(jp.getText());
                        }
                        scopeToIdList.put(dataKeyScope, idList.toArray(new String[idList.size()]));
                    }
                    dataMap.put(dataKeySlot, scopeToIdList);
                }
                response.setData(dataMap);
            }
        }

        return response;
    }
