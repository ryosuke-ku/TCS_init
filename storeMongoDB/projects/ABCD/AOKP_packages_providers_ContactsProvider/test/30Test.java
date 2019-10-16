    public void testPhoneLookupUseStrictPhoneNumberCompare() {
        // Test lookup cases when mUseStrictPhoneNumberComparison is true
        final ContactsProvider2 cp = (ContactsProvider2) getProvider();
        final ContactsDatabaseHelper dbHelper = cp.getThreadActiveDatabaseHelperForTest();
        // Get and save the original value of mUseStrictPhoneNumberComparison so that we
        // can restore it when we are done with the test
        final boolean oldUseStrict = dbHelper.getUseStrictPhoneNumberComparisonForTest();
        dbHelper.setUseStrictPhoneNumberComparisonForTest(true);


        try {
            String fullNumber = "01197297427289";
            ContentValues values = new ContentValues();
            values.put(RawContacts.CUSTOM_RINGTONE, "d");
            values.put(RawContacts.SEND_TO_VOICEMAIL, 1);
            long rawContactId = ContentUris.parseId(
                    mResolver.insert(RawContacts.CONTENT_URI, values));
            DataUtil.insertStructuredName(mResolver, rawContactId, "Senor", "Chang");
            insertPhoneNumber(rawContactId, fullNumber);
            insertPhoneNumber(rawContactId, "5103337596");
            insertPhoneNumber(rawContactId, "+19012345678");
            // One match for full number
            assertEquals(1, getCount(Uri.withAppendedPath(
                    PhoneLookup.CONTENT_FILTER_URI, fullNumber), null, null));

            // No matches for extra digit at the front
            assertEquals(0, getCount(Uri.withAppendedPath(
                    PhoneLookup.CONTENT_FILTER_URI, "55103337596"), null, null));
            // No matches for mispelled area code
            assertEquals(0, getCount(Uri.withAppendedPath(
                    PhoneLookup.CONTENT_FILTER_URI, "5123337596"), null, null));

            // One match for matching number with dashes
            assertEquals(1, getCount(Uri.withAppendedPath(
                    PhoneLookup.CONTENT_FILTER_URI, "510-333-7596"), null, null));

            // One match for matching number with international code
            assertEquals(1, getCount(Uri.withAppendedPath(
                    PhoneLookup.CONTENT_FILTER_URI, "+1-510-333-7596"), null, null));
            values.clear();

            // No matches for extra 0 in front
            assertEquals(0, getCount(Uri.withAppendedPath(
                    PhoneLookup.CONTENT_FILTER_URI, "0-510-333-7596"), null, null));
            values.clear();

            // No matches for different country code
            assertEquals(0, getCount(Uri.withAppendedPath(
                    PhoneLookup.CONTENT_FILTER_URI, "+819012345678"), null, null));
            values.clear();
        } finally {
            // restore the original value of mUseStrictPhoneNumberComparison
            // upon test completion or failure
            dbHelper.setUseStrictPhoneNumberComparisonForTest(oldUseStrict);
        }
    }
