    public void testGetContentValuesFromModel() {
        mActivity = buildTestContext();
        mHelper = new EditEventHelper(mActivity, null);
        mExpectedValues = buildTestValues();
        mModel1 = buildTestModel();

        ContentValues values = mHelper.getContentValuesFromModel(mModel1);
        assertEquals(mExpectedValues, values);

        mModel1.mRrule = null;
        mModel1.mEnd = TEST_END;

        mExpectedValues.put(Events.RRULE, (String) null);
        mExpectedValues.put(Events.DURATION, (String) null);
        mExpectedValues.put(Events.DTEND, TEST_END); // UTC time

        values = mHelper.getContentValuesFromModel(mModel1);
        assertEquals(mExpectedValues, values);

        mModel1.mAllDay = false;

        mExpectedValues.put(Events.ALL_DAY, 0);
        mExpectedValues.put(Events.DTSTART, TEST_START);
        mExpectedValues.put(Events.DTEND, TEST_END);
        // not an allday event so timezone isn't modified
        mExpectedValues.put(Events.EVENT_TIMEZONE, "UTC");

        values = mHelper.getContentValuesFromModel(mModel1);
        assertEquals(mExpectedValues, values);
    }
