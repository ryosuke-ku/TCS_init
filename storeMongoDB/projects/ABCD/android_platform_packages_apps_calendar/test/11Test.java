    public void testUpdateRecurrenceRule() {
        int selection = EditEventHelper.DOES_NOT_REPEAT;
        int weekStart = Calendar.SUNDAY;
        mModel1 = new CalendarEventModel();
        mModel1.mTimezone = Time.TIMEZONE_UTC;
        mModel1.mStart = 1272665741000L; // Fri, April 30th ~ 3:17PM

        mModel1.mRrule = "This should go away";

        EditEventHelper.updateRecurrenceRule(selection, mModel1, weekStart);
        assertNull(mModel1.mRrule);

        mModel1.mRrule = "This shouldn't change";
        selection = EditEventHelper.REPEATS_CUSTOM;

        EditEventHelper.updateRecurrenceRule(selection, mModel1, weekStart);
        assertEquals("This shouldn't change", mModel1.mRrule);

        selection = EditEventHelper.REPEATS_DAILY;

        EditEventHelper.updateRecurrenceRule(selection, mModel1, weekStart);
        assertEquals("FREQ=DAILY;WKST=SU", mModel1.mRrule);

        selection = EditEventHelper.REPEATS_EVERY_WEEKDAY;

        EditEventHelper.updateRecurrenceRule(selection, mModel1, weekStart);
        assertEquals("FREQ=WEEKLY;WKST=SU;BYDAY=MO,TU,WE,TH,FR", mModel1.mRrule);

        selection = EditEventHelper.REPEATS_WEEKLY_ON_DAY;

        EditEventHelper.updateRecurrenceRule(selection, mModel1, weekStart);
        assertEquals("FREQ=WEEKLY;WKST=SU;BYDAY=FR", mModel1.mRrule);

        selection = EditEventHelper.REPEATS_MONTHLY_ON_DAY;

        EditEventHelper.updateRecurrenceRule(selection, mModel1, weekStart);
        assertEquals("FREQ=MONTHLY;WKST=SU;BYMONTHDAY=30", mModel1.mRrule);

        selection = EditEventHelper.REPEATS_MONTHLY_ON_DAY_COUNT;

        EditEventHelper.updateRecurrenceRule(selection, mModel1, weekStart);
        assertEquals("FREQ=MONTHLY;WKST=SU;BYDAY=-1FR", mModel1.mRrule);

        selection = EditEventHelper.REPEATS_YEARLY;

        EditEventHelper.updateRecurrenceRule(selection, mModel1, weekStart);
        assertEquals("FREQ=YEARLY;WKST=SU", mModel1.mRrule);
    }
