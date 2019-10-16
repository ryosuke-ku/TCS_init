    public void testGetCause() {
        final NullPointerException npe = new NullPointerException("npe");
        ExceptionEvent ee = new ExceptionEvent(npe);
        Assert.assertSame(npe, ee.getCause());
        ee = new ExceptionEvent(npe, new Date());
        Assert.assertSame(npe, ee.getCause());
    }
