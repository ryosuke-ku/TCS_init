    public void testEmailFilterSortOrderWithOldHistory() {
        long rawContactId1 = RawContactUtil.createRawContact(mResolver);
        long dataId1 = ContentUris.parseId(insertEmail(rawContactId1, "address1@email.com"));
        long dataId2 = ContentUris.parseId(insertEmail(rawContactId1, "address2@email.com"));
        long dataId3 = ContentUris.parseId(insertEmail(rawContactId1, "address3@email.com"));
        long dataId4 = ContentUris.parseId(insertEmail(rawContactId1, "address4@email.com"));

        Uri filterUri1 = Uri.withAppendedPath(Email.CONTENT_FILTER_URI, "address");

        ContentValues v1 = new ContentValues();
        v1.put(Email.ADDRESS, "address1@email.com");
        ContentValues v2 = new ContentValues();
        v2.put(Email.ADDRESS, "address2@email.com");
        ContentValues v3 = new ContentValues();
        v3.put(Email.ADDRESS, "address3@email.com");
        ContentValues v4 = new ContentValues();
        v4.put(Email.ADDRESS, "address4@email.com");

        final ContactsProvider2 provider = (ContactsProvider2) getProvider();

        long nowInMillis = System.currentTimeMillis();
        long yesterdayInMillis = (nowInMillis - 24 * 60 * 60 * 1000);
        long sevenDaysAgoInMillis = (nowInMillis - 7 * 24 * 60 * 60 * 1000);
        long oneYearAgoInMillis = (nowInMillis - 365L * 24 * 60 * 60 * 1000);

        // address4 is contacted just once yesterday.
        provider.updateDataUsageStat(Arrays.asList(dataId4),
                DataUsageFeedback.USAGE_TYPE_LONG_TEXT, yesterdayInMillis);

        // address3 is contacted twice 1 week ago.
        provider.updateDataUsageStat(Arrays.asList(dataId3),
                DataUsageFeedback.USAGE_TYPE_LONG_TEXT, sevenDaysAgoInMillis);
        provider.updateDataUsageStat(Arrays.asList(dataId3),
                DataUsageFeedback.USAGE_TYPE_LONG_TEXT, sevenDaysAgoInMillis);

        // address2 is contacted three times 1 year ago.
        provider.updateDataUsageStat(Arrays.asList(dataId2),
                DataUsageFeedback.USAGE_TYPE_LONG_TEXT, oneYearAgoInMillis);
        provider.updateDataUsageStat(Arrays.asList(dataId2),
                DataUsageFeedback.USAGE_TYPE_LONG_TEXT, oneYearAgoInMillis);
        provider.updateDataUsageStat(Arrays.asList(dataId2),
                DataUsageFeedback.USAGE_TYPE_LONG_TEXT, oneYearAgoInMillis);

        // auto-complete should prefer recently contacted methods
        assertStoredValuesOrderly(filterUri1, new ContentValues[] { v4, v3, v2, v1 });

        // Pretend address2 is contacted right now
        provider.updateDataUsageStat(Arrays.asList(dataId2),
                DataUsageFeedback.USAGE_TYPE_LONG_TEXT, nowInMillis);

        // Now address2 is the most recently used address
        assertStoredValuesOrderly(filterUri1, new ContentValues[] { v2, v4, v3, v1 });

        // Pretend address1 is contacted right now
        provider.updateDataUsageStat(Arrays.asList(dataId1),
                DataUsageFeedback.USAGE_TYPE_LONG_TEXT, nowInMillis);

        // address2 is preferred to address1 as address2 is used 4 times in total
        assertStoredValuesOrderly(filterUri1, new ContentValues[] { v2, v1, v4, v3 });
    }
