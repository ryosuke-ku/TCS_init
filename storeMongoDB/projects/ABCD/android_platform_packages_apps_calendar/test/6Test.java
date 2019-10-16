    public void testIsSameEvent() {
        mModel1 = new CalendarEventModel();
        mModel2 = new CalendarEventModel();

        mModel1.mId = 1;
        mModel1.mCalendarId = 1;
        mModel2.mId = 1;
        mModel2.mCalendarId = 1;

        // considered the same if the event and calendar ids both match
        assertTrue(EditEventHelper.isSameEvent(mModel1, mModel2));

        mModel2.mId = 2;
        assertFalse(EditEventHelper.isSameEvent(mModel1, mModel2));

        mModel2.mId = 1;
        mModel2.mCalendarId = 2;
        assertFalse(EditEventHelper.isSameEvent(mModel1, mModel2));
    }
