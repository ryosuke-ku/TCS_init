    public void testWSPinValue() {
        TagValue t = new TagValue("\r\n\tp  = \r\n hi \thi hi \t hi\t");
        Assert.assertEquals("hi \thi hi \t hi", t.getValue("p"));
    }
