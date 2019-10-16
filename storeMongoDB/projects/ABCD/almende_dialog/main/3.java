    public RestResponse getDDRRecordsQuantity(@Name("accountId") String accountId,
        @Name("adapterTypes") @Optional Collection<AdapterType> adapterTypes,
        @Name("adapterIds") @Optional Collection<String> adapterIds, @Name("fromAddress") @Optional String fromAddress,
        @Name("toAddress") @Optional String toAddress, @Name("typeId") @Optional Collection<String> typeIds,
        @Name("communicationStatus") @Optional CommunicationStatus status, @Name("startTime") Long startTime,
        @Name("endTime") Long endTime, @Name("sessionKeys") @Optional Collection<String> sessionKeys,
        @Name("offset") @Optional Integer offset) throws Exception {

        Integer ddrRecordsQuantity = DDRRecord.getDDRRecordsQuantity(accountId, adapterTypes, adapterIds, fromAddress,
            toAddress, typeIds, status, startTime, endTime, sessionKeys, offset);
        return RestResponse.ok(Settings.DIALOG_HANDLER_VERSION, ddrRecordsQuantity);
    }
