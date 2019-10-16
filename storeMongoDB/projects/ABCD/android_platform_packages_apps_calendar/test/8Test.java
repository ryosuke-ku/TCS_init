    private boolean verifySaveEventModifyAllFollowingWithRecurring(
            ArrayList<ContentProviderOperation> ops) {
        ArrayList<ContentProviderOperation> expectedOps = new ArrayList<ContentProviderOperation>();
        int br_id = 0;
        mExpectedValues = buildTestValues();
        mExpectedValues.put(Events.HAS_ALARM, 0);
        moveExpectedTimeValuesForwardOneDay();
        mExpectedValues.put(Events.DTEND, (Long)null);
        // This is tested elsewhere, used for convenience here
        mHelper.updatePastEvents(expectedOps, mModel2, mModel1.mOriginalStart);

        br_id = expectedOps.size();
        expectedOps.add(ContentProviderOperation
                .newInsert(Events.CONTENT_URI)
                .withValues(mExpectedValues)
                .build());

        // This call has a separate unit test so we'll use it to simplify making the expected vals
        mHelper.saveRemindersWithBackRef(expectedOps, br_id, mModel1.mReminders,
                mModel2.mReminders, true);

        addOwnerAttendeeToOps(expectedOps, br_id);

        addTestAttendees(expectedOps, true, br_id);

        assertEquals(expectedOps, ops);
        return true;
    }
