    public void testGetDatePattern_各パターン確認()
    {
        assertEquals("yyyyMMdd", TimeUnitUtil.getDatePattern(TimeUnit.DAYS));
        assertEquals("yyyyMMddHH", TimeUnitUtil.getDatePattern(TimeUnit.HOURS));
        assertEquals("yyyyMMddHHmm", TimeUnitUtil.getDatePattern(TimeUnit.MINUTES));
        assertEquals("yyyyMMddHHmmss", TimeUnitUtil.getDatePattern(TimeUnit.SECONDS));
        assertEquals("yyyyMMddHHmmssSSS", TimeUnitUtil.getDatePattern(TimeUnit.MILLISECONDS));
        assertEquals(null, TimeUnitUtil.getDatePattern(TimeUnit.MICROSECONDS));
        assertEquals(null, TimeUnitUtil.getDatePattern(TimeUnit.NANOSECONDS));
    }
