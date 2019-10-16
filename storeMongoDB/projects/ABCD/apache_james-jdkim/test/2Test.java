    public void testTagSetWithEquals() {
        TagValue tv = new TagValue("t=value; v=encoded=40value");
        Set<String> tags = tv.getTags();
        Assert.assertEquals(2, tags.size());
        Assert.assertTrue(tags.contains("t"));
        Assert.assertTrue(tags.contains("v"));
    }
