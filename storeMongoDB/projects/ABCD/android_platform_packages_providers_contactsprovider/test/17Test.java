    public void testMarkAsMetadataDirtyForUsageStatsChange() {
        // Enable metadataSync flag.
        final ContactsProvider2 cp = (ContactsProvider2) getProvider();
        cp.setMetadataSyncForTest(true);

        final long rid1 = RawContactUtil.createRawContactWithName(mResolver, "contact", "a");
        final long did1a = ContentUris.parseId(insertEmail(rid1, "email_1_a@email.com"));
        updateDataUsageFeedback(DataUsageFeedback.USAGE_TYPE_LONG_TEXT, did1a);

        assertMetadataDirty(ContentUris.withAppendedId(RawContacts.CONTENT_URI, rid1),
                true);
        assertMetadataNetworkNotified(true);
    }
