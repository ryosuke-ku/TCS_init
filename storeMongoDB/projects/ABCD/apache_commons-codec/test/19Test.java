    public void testGetCharsetName() {
        Assert.assertEquals(Charsets.UTF_8.name(), new Hex(Charsets.UTF_8).getCharsetName());
    }
