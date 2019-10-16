    public void testQueryContactStrequentFrequentOrder() {
        // Prepare test data
        final long rid1 = createRawContact();
        final long did1 = ContentUris.parseId(insertPhoneNumber(rid1, "1"));
        final long did1e = ContentUris.parseId(insertEmail(rid1, "1@email.com"));

        final long rid2 = createRawContact();
        final long did2 = ContentUris.parseId(insertPhoneNumber(rid2, "2"));

        final long rid3 = createRawContact();
        final long did3 = ContentUris.parseId(insertPhoneNumber(rid3, "3"));

        final long rid4 = createRawContact();
        final long did4 = ContentUris.parseId(insertPhoneNumber(rid4, "4"));

        final long rid5 = createRawContact();
        final long did5 = ContentUris.parseId(insertPhoneNumber(rid5, "5"));

        final long rid6 = createRawContact();
        final long did6 = ContentUris.parseId(insertPhoneNumber(rid6, "6"));

        final long cid1 = queryContactId(rid1);
        final long cid2 = queryContactId(rid2);
        final long cid3 = queryContactId(rid3);
        final long cid4 = queryContactId(rid4);
        final long cid5 = queryContactId(rid5);
        final long cid6 = queryContactId(rid6);

        // Make sure they aren't aggregated.
        EvenMoreAsserts.assertUnique(cid1, cid2, cid3, cid4, cid5, cid6);

        // Prepare the clock
        sMockClock.install();

        // We check the timestamp in SQL, which doesn't know about the MockClock.  So we need to
        // use the  actual (roughly) time.

        final long nowInMillis = System.currentTimeMillis();
        final long yesterdayInMillis = (nowInMillis - 24 * 60 * 60 * 1000);
        final long sevenDaysAgoInMillis = (nowInMillis - 7 * 24 * 60 * 60 * 1000);
        final long oneYearAgoInMillis = (nowInMillis - 365L * 24 * 60 * 60 * 1000);

        // A year ago...
        sMockClock.setCurrentTimeMillis(oneYearAgoInMillis);

        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did1, did2);
        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did1);

        // 7 days ago...
        sMockClock.setCurrentTimeMillis(sevenDaysAgoInMillis);

        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did3, did4);
        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did3);

        // Yesterday...
        sMockClock.setCurrentTimeMillis(yesterdayInMillis);

        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did5, did6);
        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did5);

        // Contact cid1 again, but it's an email, not a phone call.
        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_LONG_TEXT, did1e);

        // Check the order -- The regular frequent, which is contact based.
        // Note because we contacted cid1 yesterday, it's been contacted 3 times, so it comes
        // first.
        assertStoredValuesOrderly(Contacts.CONTENT_STREQUENT_URI,
                cv(Contacts._ID, cid1),
                cv(Contacts._ID, cid5),
                cv(Contacts._ID, cid6),
                cv(Contacts._ID, cid3),
                cv(Contacts._ID, cid4),
                cv(Contacts._ID, cid2));

        // Check the order -- phone only frequent, which is data based.
        // Note this is based on data, and only looks at phone numbers, so the order is different
        // now.
        assertStoredValuesOrderly(Contacts.CONTENT_STREQUENT_URI.buildUpon()
                    .appendQueryParameter(ContactsContract.STREQUENT_PHONE_ONLY, "1").build(),
                cv(Data._ID, did5),
                cv(Data._ID, did6),
                cv(Data._ID, did3),
                cv(Data._ID, did4),
                cv(Data._ID, did1),
                cv(Data._ID, did2));
    }
