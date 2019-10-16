    public VdcQueryReturnValue runInternalQuery(VdcQueryType actionType, VdcQueryParametersBase parameters) {
        return runQueryImpl(actionType, parameters, false);
    }
