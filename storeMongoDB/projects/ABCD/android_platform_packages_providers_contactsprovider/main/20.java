    void updateFromMetaDataEntry(SQLiteDatabase db, MetadataEntry metadataEntry) {
        final RawContactInfo rawContactInfo =  metadataEntry.mRawContactInfo;
        final long rawContactId = searchRawContactIdForRawContactInfo(db, rawContactInfo);
        if (rawContactId == 0) {
            return;
        }

        ContentValues rawContactValues = new ContentValues();
        rawContactValues.put(RawContacts.SEND_TO_VOICEMAIL, metadataEntry.mSendToVoicemail);
        rawContactValues.put(RawContacts.STARRED, metadataEntry.mStarred);
        rawContactValues.put(RawContacts.PINNED, metadataEntry.mPinned);
        updateRawContact(db, rawContactId, rawContactValues, /* callerIsSyncAdapter =*/true,
                /* callerIsMetadataSyncAdapter =*/true);

        // Update Data and DataUsageStats table.
        for (int i = 0; i < metadataEntry.mFieldDatas.size(); i++) {
            final FieldData fieldData = metadataEntry.mFieldDatas.get(i);
            final String dataHashId = fieldData.mDataHashId;
            final ArrayList<Long> dataIds = queryDataId(db, rawContactId, dataHashId);

            for (long dataId : dataIds) {
                // Update is_primary and is_super_primary.
                ContentValues dataValues = new ContentValues();
                dataValues.put(Data.IS_PRIMARY, fieldData.mIsPrimary ? 1 : 0);
                dataValues.put(Data.IS_SUPER_PRIMARY, fieldData.mIsSuperPrimary ? 1 : 0);
                updateData(ContentUris.withAppendedId(Data.CONTENT_URI, dataId),
                        dataValues, null, null, /* callerIsSyncAdapter =*/true,
                        /* callerIsMetadataSyncAdapter =*/true);

                // Update UsageStats.
                for (int j = 0; j < fieldData.mUsageStatsList.size(); j++) {
                    final UsageStats usageStats = fieldData.mUsageStatsList.get(j);
                    final String usageType = usageStats.mUsageType;
                    final int typeInt = getDataUsageFeedbackType(usageType.toLowerCase(), null);
                    final long lastTimeUsed = usageStats.mLastTimeUsed;
                    final int timesUsed = usageStats.mTimesUsed;
                    ContentValues usageStatsValues = new ContentValues();
                    usageStatsValues.put(DataUsageStatColumns.DATA_ID, dataId);
                    usageStatsValues.put(DataUsageStatColumns.USAGE_TYPE_INT, typeInt);
                    usageStatsValues.put(DataUsageStatColumns.LAST_TIME_USED, lastTimeUsed);
                    usageStatsValues.put(DataUsageStatColumns.TIMES_USED, timesUsed);
                    updateDataUsageStats(db, usageStatsValues);
                }
            }
        }

        // Update AggregationException table.
        final Set<Long> aggregationRawContactIdsInServer = new HashSet<>();
        for (int i = 0; i < metadataEntry.mAggregationDatas.size(); i++) {
            final AggregationData aggregationData = metadataEntry.mAggregationDatas.get(i);
            final int typeInt = getAggregationType(aggregationData.mType, null);
            final RawContactInfo aggregationContact1 = aggregationData.mRawContactInfo1;
            final RawContactInfo aggregationContact2 = aggregationData.mRawContactInfo2;
            final long rawContactId1 = searchRawContactIdForRawContactInfo(db, aggregationContact1);
            final long rawContactId2 = searchRawContactIdForRawContactInfo(db, aggregationContact2);
            if (rawContactId1 == 0 || rawContactId2 == 0) {
                continue;
            }
            ContentValues values = new ContentValues();
            values.put(AggregationExceptions.RAW_CONTACT_ID1, rawContactId1);
            values.put(AggregationExceptions.RAW_CONTACT_ID2, rawContactId2);
            values.put(AggregationExceptions.TYPE, typeInt);
            updateAggregationException(db, values, /*callerIsSyncAdapter=*/true,
                    /* callerIsMetadataSyncAdapter =*/true);
            if (rawContactId1 != rawContactId) {
                aggregationRawContactIdsInServer.add(rawContactId1);
            }
            if (rawContactId2 != rawContactId) {
                aggregationRawContactIdsInServer.add(rawContactId2);
            }
        }

        // Delete AggregationExceptions from CP2 if it doesn't exist in server side.
        Set<Long> aggregationRawContactIdsInLocal = queryAggregationRawContactIds(db, rawContactId);
        Set<Long> rawContactIdsToBeDeleted = com.google.common.collect.Sets.difference(
                aggregationRawContactIdsInLocal, aggregationRawContactIdsInServer);
        for (Long deleteRawContactId : rawContactIdsToBeDeleted) {
            ContentValues values = new ContentValues();
            values.put(AggregationExceptions.RAW_CONTACT_ID1, rawContactId);
            values.put(AggregationExceptions.RAW_CONTACT_ID2, deleteRawContactId);
            values.put(AggregationExceptions.TYPE, AggregationExceptions.TYPE_AUTOMATIC);
            updateAggregationException(db, values, /*callerIsSyncAdapter=*/true,
                    /* callerIsMetadataSyncAdapter =*/true);
        }
    }
