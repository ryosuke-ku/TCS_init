    public void testUpdateFromMetadataEntry() {
        String accountType1 = "accountType1";
        String accountName1 = "accountName1";
        String dataSet1 = "plus";
        Account account1 = new Account(accountName1, accountType1);
        long rawContactId = RawContactUtil.createRawContactWithName(mResolver, account1);
        Uri rawContactUri = ContentUris.withAppendedId(RawContacts.CONTENT_URI, rawContactId);
        // Add backup_id for the raw contact.
        String backupId = "backupId100001";
        ContentValues values = new ContentValues();
        values.put(RawContacts.BACKUP_ID, backupId);
        assertEquals(1, mResolver.update(rawContactUri, values, null, null));

        String emailAddress = "address@email.com";
        Uri dataUri = insertEmail(rawContactId, emailAddress);
        String hashId = getStoredValue(dataUri, Data.HASH_ID);

        // Another data that should not be updated.
        String phoneNumber = "111-111-1111";
        Uri dataUri2 = insertPhoneNumber(rawContactId, phoneNumber);

        // Aggregation should be deleted from local since it doesn't exist in server.
        long toBeDeletedAggRawContactId = RawContactUtil.createRawContactWithName(
                mResolver, account1);
        setAggregationException(AggregationExceptions.TYPE_KEEP_SEPARATE,
                rawContactId, toBeDeletedAggRawContactId);

        // Check if AggregationException table has one value.
        assertStoredValue(AggregationExceptions.CONTENT_URI, AggregationExceptions.RAW_CONTACT_ID1,
                rawContactId);
        assertStoredValue(AggregationExceptions.CONTENT_URI, AggregationExceptions.RAW_CONTACT_ID2,
                toBeDeletedAggRawContactId);
        assertStoredValue(AggregationExceptions.CONTENT_URI, AggregationExceptions.TYPE,
                AggregationExceptions.TYPE_KEEP_SEPARATE);

        String accountType2 = "accountType2";
        String accountName2 = "accountName2";
        Account account2 = new Account(accountName2, accountType2);
        long rawContactId2 = RawContactUtil.createRawContactWithName(mResolver, account2);
        Uri rawContactUri2 = ContentUris.withAppendedId(RawContacts.CONTENT_URI, rawContactId2);
        String backupId2 = "backupId100003";
        ContentValues values2 = new ContentValues();
        values2.put(RawContacts.BACKUP_ID, backupId2);
        assertEquals(1, mResolver.update(rawContactUri2, values2, null, null));

        String usageTypeString = "CALL";
        int lastTimeUsed = 1111111;
        int timesUsed = 5;
        String aggregationTypeString = "TOGETHER";
        int aggregationType = AggregationExceptions.TYPE_KEEP_TOGETHER;

        RawContactInfo rawContactInfo = new RawContactInfo(
                backupId, accountType1, accountName1, null);
        UsageStats usageStats = new UsageStats(usageTypeString, lastTimeUsed, timesUsed);
        ArrayList<UsageStats> usageStatsList = new ArrayList<>();
        usageStatsList.add(usageStats);
        FieldData fieldData = new FieldData(hashId, true, true, usageStatsList);
        ArrayList<FieldData> fieldDataList = new ArrayList<>();
        fieldDataList.add(fieldData);
        ArrayList<AggregationData> aggregationDataList = new ArrayList<>();
        MetadataEntry metadataEntry = new MetadataEntry(rawContactInfo,
                1, 1, 1, fieldDataList, aggregationDataList);

        ContactsProvider2 provider = (ContactsProvider2) getProvider();
        final ContactsDatabaseHelper helper =
                ((ContactsDatabaseHelper) provider.getDatabaseHelper());
        SQLiteDatabase db = helper.getWritableDatabase();

        // Before updating tables from MetadataEntry.
        assertStoredValue(rawContactUri, RawContacts.ACCOUNT_TYPE, accountType1);
        assertStoredValue(rawContactUri, RawContacts.ACCOUNT_NAME, accountName1);
        assertStoredValue(rawContactUri, RawContacts.SEND_TO_VOICEMAIL, "0");
        assertStoredValue(rawContactUri, RawContacts.STARRED, "0");
        assertStoredValue(rawContactUri, RawContacts.PINNED, "0");
        assertStoredValue(dataUri, Data.IS_PRIMARY, 0);
        assertStoredValue(dataUri, Data.IS_SUPER_PRIMARY, 0);

        // Update tables without aggregation first, since aggregator will affect pinned value.
        provider.updateFromMetaDataEntry(db, metadataEntry);

        // After updating tables from MetadataEntry.
        assertStoredValue(rawContactUri, RawContacts.ACCOUNT_TYPE, accountType1);
        assertStoredValue(rawContactUri, RawContacts.ACCOUNT_NAME, accountName1);
        assertStoredValue(rawContactUri, RawContacts.SEND_TO_VOICEMAIL, "1");
        assertStoredValue(rawContactUri, RawContacts.STARRED, "1");
        assertStoredValue(rawContactUri, RawContacts.PINNED, "1");
        assertStoredValue(dataUri, Data.IS_PRIMARY, 1);
        assertStoredValue(dataUri, Data.IS_SUPER_PRIMARY, 1);
        assertStoredValue(dataUri2, Data.IS_PRIMARY, 0);
        assertStoredValue(dataUri2, Data.IS_SUPER_PRIMARY, 0);
        final Uri dataUriWithUsageType = Data.CONTENT_URI.buildUpon().appendQueryParameter(
                DataUsageFeedback.USAGE_TYPE, usageTypeString).build();
        assertDataUsageCursorContains(dataUriWithUsageType, emailAddress, timesUsed, lastTimeUsed);

        // Update AggregationException table.
        RawContactInfo aggregationContact = new RawContactInfo(
                backupId2, accountType2, accountName2, null);
        AggregationData aggregationData = new AggregationData(
                rawContactInfo, aggregationContact, aggregationTypeString);
        aggregationDataList.add(aggregationData);
        metadataEntry = new MetadataEntry(rawContactInfo,
                1, 1, 1, fieldDataList, aggregationDataList);
        provider.updateFromMetaDataEntry(db, metadataEntry);

        // Check if AggregationException table is updated.
        assertStoredValue(AggregationExceptions.CONTENT_URI, AggregationExceptions.RAW_CONTACT_ID1,
                rawContactId);
        assertStoredValue(AggregationExceptions.CONTENT_URI, AggregationExceptions.RAW_CONTACT_ID2,
                rawContactId2);
        assertStoredValue(AggregationExceptions.CONTENT_URI, AggregationExceptions.TYPE,
                aggregationType);

        // After aggregation, check if rawContacts.starred/send_to_voicemail
        // were copied to contacts table.
        final long contactId = queryContactId(rawContactId);
        Uri contactUri = ContentUris.withAppendedId(Contacts.CONTENT_URI, contactId);
        // The merged contact should be starred if any of the rawcontact is starred.
        assertStoredValue(contactUri, Contacts.STARRED, 1);
        // The merged contact should be send_to_voicemail
        // if all of the rawcontact is send_to_voicemail.
        assertStoredValue(contactUri, Contacts.SEND_TO_VOICEMAIL, 0);
    }
