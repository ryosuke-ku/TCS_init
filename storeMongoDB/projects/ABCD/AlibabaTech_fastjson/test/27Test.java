    public void test_writeTo_error() throws Exception {
        JSONException error = null;
        try {
            JSONObject json = new JSONObject();
            json.writeJSONString(new ErrorAppendable());
        } catch (JSONException e) {
            error = e;
        }
        Assert.assertNotNull(error);
    }
