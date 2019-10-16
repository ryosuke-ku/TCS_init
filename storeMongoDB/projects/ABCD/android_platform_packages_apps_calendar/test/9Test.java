    public void testIsFirstEventInSeries() {
        mModel1 = new CalendarEventModel();
        mModel2 = new CalendarEventModel();

        // It's considered the first event if the original start of the new model matches the
        // start of the old model
        mModel1.mOriginalStart = 100;
        mModel1.mStart = 200;
        mModel2.mOriginalStart = 100;
        mModel2.mStart = 100;

        assertTrue(EditEventHelper.isFirstEventInSeries(mModel1, mModel2));

        mModel1.mOriginalStart = 80;
        assertFalse(EditEventHelper.isFirstEventInSeries(mModel1, mModel2));
    }
