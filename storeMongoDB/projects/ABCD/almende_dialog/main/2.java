    public Object getDDRRecords(@Name("accountId") String accountId,
        @Name("adapterTypes") @Optional Collection<AdapterType> adapterTypes,
        @Name("adapterIds") @Optional Collection<String> adapterIds, @Name("fromAddress") @Optional String fromAddress,
        @Name("toAddress") @Optional String toAddress, @Name("typeId") @Optional Collection<String> typeIds,
        @Name("communicationStatus") @Optional String status, @Name("startTime") @Optional Long startTime,
        @Name("endTime") @Optional Long endTime, @Name("sessionKeys") @Optional Collection<String> sessionKeys,
        @Name("offset") @Optional Integer offset, @Name("limit") @Optional Integer limit,
        @Name("shouldGenerateCosts") @Optional Boolean shouldGenerateCosts,
        @Name("shouldIncludeServiceCosts") @Optional Boolean shouldIncludeServiceCosts) throws Exception {

        CommunicationStatus communicationStatus = status != null && !status.isEmpty()
            ? CommunicationStatus.fromJson(status) : null;
        return DDRRecord.getDDRRecords(accountId, adapterTypes, adapterIds, fromAddress, toAddress, typeIds,
            communicationStatus, startTime, endTime, sessionKeys, offset, limit, shouldGenerateCosts,
            shouldIncludeServiceCosts);
    }
