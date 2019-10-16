    public void test_json() throws Exception {
        Assert.assertSame(MediaType.APPLICATION_XML_TYPE, MediaTypeProvider.getInstance().fromString(MediaType.APPLICATION_XML));
        Assert.assertSame(MediaType.APPLICATION_JSON_TYPE, MediaTypeProvider.getInstance().fromString(MediaType.APPLICATION_JSON));
    }
