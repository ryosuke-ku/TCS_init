    public void test_2() throws Exception {
        JSONArray array = new JSONArray();
        array.add(123);
        array.add("222");
        array.add(3);
        array.add(true);
        array.add("true");
        array.add(null);

        Assert.assertEquals(123, array.getByte(0).byteValue());
        Assert.assertEquals(123, array.getByteValue(0));

        Assert.assertEquals(123, array.getShort(0).shortValue());
        Assert.assertEquals(123, array.getShortValue(0));

        Assert.assertEquals(123F, array.getFloat(0).floatValue());
        Assert.assertEquals(123F, array.getFloatValue(0));

        Assert.assertEquals(123D, array.getDouble(0).doubleValue());
        Assert.assertEquals(123D, array.getDoubleValue(0));

        Assert.assertEquals(123, array.getIntValue(0));
        Assert.assertEquals(123, array.getLongValue(0));
        Assert.assertEquals(new BigDecimal("123"), array.getBigDecimal(0));

        Assert.assertEquals(222, array.getIntValue(1));
        Assert.assertEquals(new Integer(222), array.getInteger(1));
        Assert.assertEquals(new Long(222), array.getLong(1));
        Assert.assertEquals(new BigDecimal("222"), array.getBigDecimal(1));

        Assert.assertEquals(true, array.getBooleanValue(4));
        Assert.assertEquals(Boolean.TRUE, array.getBoolean(4));

        Assert.assertEquals(0, array.getIntValue(5));
        Assert.assertEquals(0, array.getLongValue(5));
        Assert.assertEquals(null, array.getInteger(5));
        Assert.assertEquals(null, array.getLong(5));
        Assert.assertEquals(null, array.getBigDecimal(5));
        Assert.assertEquals(null, array.getBoolean(5));
        Assert.assertEquals(false, array.getBooleanValue(5));
    }
