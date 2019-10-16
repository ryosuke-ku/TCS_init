    protected void fail(TransactionAttempt tx, DrpcRequestInfo requestInfo)
    {
        if (logger.isDebugEnabled() == true)
        {
            String logFormat = "Transaction failed. TransactionAttempt={0}, DrpcRequestInfo={1}";
            logger.debug(MessageFormat.format(logFormat, tx, requestInfo));
        }

        try
        {
            this.fetchHelper.fail(requestInfo.getRequestId());
        }
        catch (TException ex)
        {
            String logFormat = "Fail notify failed. TransactionAttempt={0}, DrpcRequestInfo={1}";
            logger.warn(MessageFormat.format(logFormat, tx, requestInfo));
        }
    }
