    public void testCheckDefault() {
        Assert.assertEquals(-1, check.getThreshold(), 0);
        Assert.assertEquals(5, check.getTolerance(), 0);
        Assert.assertNull(check.valid(20));
    }
