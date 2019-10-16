    public void test_object() throws Exception {
        Assert.assertTrue(JSON.parseObject("{}").size() == 0);
        Assert.assertEquals(1, JSON.parseObject("{\"K\":3}").size());
        Assert.assertEquals(3, ((Number) JSON.parseObject("{\"K\":3}").get("K")).intValue());
        Assert.assertEquals(2, JSON.parseObject("{\"K1\":3,\"K2\":4}").size());
        Assert.assertEquals(3, ((Number) JSON.parseObject("{\"K1\":3,\"K2\":4}").get("K1")).intValue());
        Assert.assertEquals(4, ((Number) JSON.parseObject("{\"K1\":3,\"K2\":4}").get("K2")).intValue());
        Assert.assertEquals(1, JSON.parseObject("{\"K\":{}}").size());
        Assert.assertEquals(1, JSON.parseObject("{\"K\":[]}").size());
    }
