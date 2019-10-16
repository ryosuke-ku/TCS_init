    public Object getDDRRecord(@Name("ddrRecordId") String id, @Name("accountId") String accountId,
        @Name("shouldGenerateCosts") @Optional Boolean shouldGenerateCosts,
        @Name("shouldIncludeServiceCosts") @Optional Boolean shouldIncludeServiceCosts) throws Exception {

        DDRRecord ddrRecord = DDRRecord.getDDRRecord(id, accountId);
        if (ddrRecord != null) {
            ddrRecord.setShouldGenerateCosts(shouldGenerateCosts);
            ddrRecord.setShouldIncludeServiceCosts(shouldIncludeServiceCosts);
        }
        return ddrRecord;
    }
