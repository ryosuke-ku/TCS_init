    public void test_writeTo_0() throws Exception {
        SerializeWriter out = new SerializeWriter();

        JSONObject json = new JSONObject();
        json.writeJSONString(out);

        Assert.assertEquals("{}", out.toString());
    }
