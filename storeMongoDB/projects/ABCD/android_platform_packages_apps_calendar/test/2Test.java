    public void testConstructDefaultStartTime() {
        mActivity = buildTestContext();
        mHelper = new EditEventHelper(mActivity, null);

        long now = 0;
        long expected = now + 30 * DateUtils.MINUTE_IN_MILLIS;
        assertEquals(expected, mHelper.constructDefaultStartTime(now));

        // 2:00 -> 2:30
        now = 1262340000000L; // Fri Jan 01 2010 02:00:00 GMT-0800 (PST)
        expected = now + 30 * DateUtils.MINUTE_IN_MILLIS;
        assertEquals(expected, mHelper.constructDefaultStartTime(now));

        // 2:01 -> 2:30
        now += DateUtils.MINUTE_IN_MILLIS;
        assertEquals(expected, mHelper.constructDefaultStartTime(now));

        // 2:02 -> 2:30
        now += DateUtils.MINUTE_IN_MILLIS;
        assertEquals(expected, mHelper.constructDefaultStartTime(now));

        // 2:32 -> 3:00
        now += 30 * DateUtils.MINUTE_IN_MILLIS;
        expected += 30 * DateUtils.MINUTE_IN_MILLIS;
        assertEquals(expected, mHelper.constructDefaultStartTime(now));

        // 2:33 -> 3:00
        now += DateUtils.MINUTE_IN_MILLIS;
        assertEquals(expected, mHelper.constructDefaultStartTime(now));

    }
