    protected void ack(TransactionAttempt tx, DrpcRequestInfo requestInfo)
    {
        if (logger.isDebugEnabled() == true)
        {
            String logFormat = "Transaction succeeded. TransactionAttempt={0}, DrpcRequestInfo={1}";
            logger.debug(MessageFormat.format(logFormat, tx, requestInfo));
        }

        try
        {
            this.fetchHelper.ack(requestInfo.getRequestId(), "Succeeded");
        }
        catch (TException ex)
        {
            String logFormat = "Success notify failed. TransactionAttempt={0}, DrpcRequestInfo={1}";
            logger.warn(MessageFormat.format(logFormat, tx, requestInfo));
        }
    }
