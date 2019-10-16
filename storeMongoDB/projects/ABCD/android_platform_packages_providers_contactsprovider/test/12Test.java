    public void testMarkAsMetadataDirtyForAggregationExceptionChange() {
        // Enable metadataSync flag.
        final ContactsProvider2 cp = (ContactsProvider2) getProvider();
        cp.setMetadataSyncForTest(true);

        long rawContactId1 = RawContactUtil.createRawContact(mResolver, new Account("a", "a"));
        long rawContactId2 = RawContactUtil.createRawContact(mResolver, new Account("b", "b"));

        setAggregationException(AggregationExceptions.TYPE_KEEP_TOGETHER,
                rawContactId1, rawContactId2);

        assertMetadataDirty(ContentUris.withAppendedId(RawContacts.CONTENT_URI, rawContactId1),
                true);
        assertMetadataDirty(ContentUris.withAppendedId(RawContacts.CONTENT_URI, rawContactId2),
                true);
        assertMetadataNetworkNotified(true);
    }
