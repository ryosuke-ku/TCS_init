    public void testGetParentUnit_各パターン確認()
    {
        assertEquals(null, TimeUnitUtil.getParentUnit(TimeUnit.DAYS));
        assertEquals(TimeUnit.DAYS, TimeUnitUtil.getParentUnit(TimeUnit.HOURS));
        assertEquals(TimeUnit.HOURS, TimeUnitUtil.getParentUnit(TimeUnit.MINUTES));
        assertEquals(TimeUnit.MINUTES, TimeUnitUtil.getParentUnit(TimeUnit.SECONDS));
        assertEquals(TimeUnit.SECONDS, TimeUnitUtil.getParentUnit(TimeUnit.MILLISECONDS));
        assertEquals(TimeUnit.MILLISECONDS, TimeUnitUtil.getParentUnit(TimeUnit.MICROSECONDS));
        assertEquals(TimeUnit.MICROSECONDS, TimeUnitUtil.getParentUnit(TimeUnit.NANOSECONDS));
    }
