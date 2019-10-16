    public void testConstructDefaultEndTime() {
        mActivity = buildTestContext();
        mHelper = new EditEventHelper(mActivity, null);

        long start = 1262340000000L;
        long expected = start + DateUtils.HOUR_IN_MILLIS;
        assertEquals(expected, mHelper.constructDefaultEndTime(start));
    }
