    public void testSetModelFromCursor() {
        mActivity = buildTestContext();
        mHelper = new EditEventHelper(mActivity, null);
        MatrixCursor c = new MatrixCursor(EditEventHelper.EVENT_PROJECTION);
        c.addRow(TEST_CURSOR_DATA);

        mModel1 = new CalendarEventModel();
        mModel2 = buildTestModel();

        EditEventHelper.setModelFromCursor(mModel1, c);
        assertEquals(mModel1, mModel2);

        TEST_CURSOR_DATA[EditEventHelper.EVENT_INDEX_ALL_DAY] = "0";
        c.close();
        c = new MatrixCursor(EditEventHelper.EVENT_PROJECTION);
        c.addRow(TEST_CURSOR_DATA);

        mModel2.mAllDay = false;
        mModel2.mStart = TEST_START; // UTC time

        EditEventHelper.setModelFromCursor(mModel1, c);
        assertEquals(mModel1, mModel2);

        TEST_CURSOR_DATA[EditEventHelper.EVENT_INDEX_RRULE] = null;
        c.close();
        c = new MatrixCursor(EditEventHelper.EVENT_PROJECTION);
        c.addRow(TEST_CURSOR_DATA);

        mModel2.mRrule = null;
        mModel2.mEnd = TEST_END;
        mModel2.mDuration = null;

        EditEventHelper.setModelFromCursor(mModel1, c);
        assertEquals(mModel1, mModel2);

        TEST_CURSOR_DATA[EditEventHelper.EVENT_INDEX_ALL_DAY] = "1";
        c.close();
        c = new MatrixCursor(EditEventHelper.EVENT_PROJECTION);
        c.addRow(TEST_CURSOR_DATA);

        mModel2.mAllDay = true;
        mModel2.mStart = TEST_START; // Monday, May 3rd, midnight
        mModel2.mEnd = TEST_END; // Tuesday, May 4th, midnight

        EditEventHelper.setModelFromCursor(mModel1, c);
        assertEquals(mModel1, mModel2);
    }
