    public void testAddRecurrenceRule() {
        mActivity = buildTestContext();
        mHelper = new EditEventHelper(mActivity, null);
        mValues = new ContentValues();
        mExpectedValues = new ContentValues();
        mModel1 = new CalendarEventModel();

        mExpectedValues.put(Events.RRULE, "Weekly, Monday");
        mExpectedValues.put(Events.DURATION, "P60S");
        mExpectedValues.put(Events.DTEND, (Long) null);

        mModel1.mRrule = "Weekly, Monday";
        mModel1.mStart = 1;
        mModel1.mEnd = 60001;
        mModel1.mAllDay = false;

        mHelper.addRecurrenceRule(mValues, mModel1);
        assertEquals(mExpectedValues, mValues);

        mExpectedValues.put(Events.DURATION, "P1D");

        mModel1.mAllDay = true;
        mValues.clear();

        mHelper.addRecurrenceRule(mValues, mModel1);
        assertEquals(mExpectedValues, mValues);

    }
