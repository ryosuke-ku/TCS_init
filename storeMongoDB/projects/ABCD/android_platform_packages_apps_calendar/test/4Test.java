    private boolean verifySaveEventModifyRecurring(ArrayList<ContentProviderOperation> ops) {
        ArrayList<ContentProviderOperation> expectedOps = new ArrayList<ContentProviderOperation>();
        int br_id = 0;
        mExpectedValues = buildTestValues();
        mExpectedValues.put(Events.HAS_ALARM, 0);
        // This is tested elsewhere, used for convenience here
        mHelper.checkTimeDependentFields(mModel2, mModel1, mExpectedValues,
                EditEventHelper.MODIFY_ALL);

        expectedOps.add(ContentProviderOperation.newUpdate(Uri.parse(mModel1.mUri)).withValues(
                mExpectedValues).build());

        // This call has a separate unit test so we'll use it to simplify making the expected vals
        mHelper.saveReminders(expectedOps, TEST_EVENT_ID, mModel1.mReminders,
                mModel2.mReminders, false);

        addOwnerAttendeeToOps(expectedOps);
        addAttendeeChangesOps(expectedOps);

        assertEquals(expectedOps, ops);
        return true;
    }
