    public void testQueryContactStrequentFrequentOrder() {
        // Prepare test data
        final long rid1 = RawContactUtil.createRawContact(mResolver);
        final long did1 = ContentUris.parseId(insertPhoneNumber(rid1, "1"));
        final long did1e = ContentUris.parseId(insertEmail(rid1, "1@email.com"));

        final long rid2 = RawContactUtil.createRawContact(mResolver);
        final long did2 = ContentUris.parseId(insertPhoneNumber(rid2, "2"));

        final long rid3 = RawContactUtil.createRawContact(mResolver);
        final long did3 = ContentUris.parseId(insertPhoneNumber(rid3, "3"));

        final long rid4 = RawContactUtil.createRawContact(mResolver);
        final long did4 = ContentUris.parseId(insertPhoneNumber(rid4, "4"));

        final long rid5 = RawContactUtil.createRawContact(mResolver);
        final long did5 = ContentUris.parseId(insertPhoneNumber(rid5, "5"));

        final long rid6 = RawContactUtil.createRawContact(mResolver);
        final long did6 = ContentUris.parseId(insertPhoneNumber(rid6, "6"));

        final long rid7 = RawContactUtil.createRawContact(mResolver);
        final long did7 = ContentUris.parseId(insertPhoneNumber(rid7, "7"));

        final long rid8 = RawContactUtil.createRawContact(mResolver);
        final long did8 = ContentUris.parseId(insertPhoneNumber(rid8, "8"));

        final long cid1 = queryContactId(rid1);
        final long cid2 = queryContactId(rid2);
        final long cid3 = queryContactId(rid3);
        final long cid4 = queryContactId(rid4);
        final long cid5 = queryContactId(rid5);
        final long cid6 = queryContactId(rid6);
        final long cid7 = queryContactId(rid7);
        final long cid8 = queryContactId(rid8);

        // Make sure they aren't aggregated.
        EvenMoreAsserts.assertUnique(cid1, cid2, cid3, cid4, cid5, cid6, cid7, cid8);

        // Prepare the clock
        sMockClock.install();

        // We check the timestamp in SQL, which doesn't know about the MockClock.  So we need to
        // use the  actual (roughly) time.

        final long nowInMillis = System.currentTimeMillis();
        final long oneDayAgoInMillis = (nowInMillis - 24L * 60 * 60 * 1000);
        final long fourDaysAgoInMillis = (nowInMillis - 4L * 24 * 60 * 60 * 1000);
        final long eightDaysAgoInMillis = (nowInMillis - 8L * 24 * 60 * 60 * 1000);
        final long fifteenDaysAgoInMillis = (nowInMillis - 15L * 24 * 60 * 60 * 1000);
        // All contacts older than 30 days will not be included in frequents
        final long thirtyOneDaysAgoInMillis = (nowInMillis - 31L * 24 * 60 * 60 * 1000);

        // Contacts in this bucket are considered more than 30 days old
        sMockClock.setCurrentTimeMillis(thirtyOneDaysAgoInMillis);

        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did1, did2);
        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did1);

        // Contacts in this bucket are considered more than 14 days old
        sMockClock.setCurrentTimeMillis(fifteenDaysAgoInMillis);

        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did3, did4);
        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did3);

        // Contacts in this bucket are considered more than 7 days old
        sMockClock.setCurrentTimeMillis(eightDaysAgoInMillis);

        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did5, did6);
        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did5);

        // Contact cid1 again, but it's an email, not a phone call.
        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_LONG_TEXT, did1e);

        // Contacts in this bucket are considered more than 3 days old
        sMockClock.setCurrentTimeMillis(fourDaysAgoInMillis);

        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did7);
        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did7);


        // Contacts in this bucket are considered less than 3 days old
        sMockClock.setCurrentTimeMillis(oneDayAgoInMillis);

        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_CALL, did8);

        sMockClock.setCurrentTimeMillis(nowInMillis);

        // Check the order -- The regular frequent, which is contact based.
        // Note because we contacted cid1 8 days ago, it's been contacted 3 times, so it comes
        // before cid5 and cid6, which were contacted at the same time.
        // cid2 will not show up because it was contacted more than 30 days ago

        assertStoredValuesOrderly(Contacts.CONTENT_STREQUENT_URI,
                cv(Contacts._ID, cid8),
                cv(Contacts._ID, cid7),
                cv(Contacts._ID, cid1),
                cv(Contacts._ID, cid5),
                cv(Contacts._ID, cid6),
                cv(Contacts._ID, cid3),
                cv(Contacts._ID, cid4));

        // Check the order -- phone only frequent, which is data based.
        // Note this is based on data, and only looks at phone numbers, so the order is different
        // now.
        // did1, did2 will not show up because they were used to make calls more than 30 days ago.
        assertStoredValuesOrderly(Contacts.CONTENT_STREQUENT_URI.buildUpon()
                    .appendQueryParameter(ContactsContract.STREQUENT_PHONE_ONLY, "1").build(),
                cv(Data._ID, did8),
                cv(Data._ID, did7),
                cv(Data._ID, did5),
                cv(Data._ID, did6),
                cv(Data._ID, did3),
                cv(Data._ID, did4));
    }
