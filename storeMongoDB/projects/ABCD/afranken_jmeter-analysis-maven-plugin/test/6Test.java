    public void testCheckUpper() {
        check.setThreshold(2000);
        check.setToleranceDirection(Check.ToleranceDirection.UPPER.toString());
        Assert.assertEquals(2000, check.getMinValue(), 0);
        Assert.assertEquals(Double.MAX_VALUE, check.getMaxValue(), 0);
        Assert.assertTrue(check.valid(2000));
        Assert.assertTrue(check.valid(2001));
        Assert.assertTrue(check.valid(2100));
        Assert.assertTrue(check.valid(2101));
        Assert.assertTrue(check.valid(Double.MAX_VALUE));
        Assert.assertFalse(check.valid(1999));
        Assert.assertFalse(check.valid(1900));
        Assert.assertFalse(check.valid(1899));
        Assert.assertFalse(check.valid(0));
    }
