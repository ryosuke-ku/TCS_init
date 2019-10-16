    public void test_all() throws Exception {
        Assert.assertEquals(null, JSON.parse(null));
        Assert.assertEquals("{}", JSON.toJSONString(new HashMap<String, Object>()));
        Assert.assertEquals("{}", JSON.toJSONString(new HashMap<String, Object>(), true));
        Assert.assertEquals("{}", JSON.toJSONString(new HashMap<String, Object>(), true));
        Assert.assertEquals(null, JSON.parseObject(null));
        Assert.assertEquals(null, JSON.parseArray(null));
        Assert.assertEquals(null, JSON.parseObject(null, Object.class));
        Assert.assertEquals(null, JSON.parseArray(null, Object.class));
    }
