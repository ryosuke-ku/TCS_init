    public VdcReturnValueBase runInternalAction(VdcActionType actionType,
            VdcActionParametersBase parameters,
            CompensationContext context) {
        return runActionImpl(actionType, parameters, true, context);
    }
