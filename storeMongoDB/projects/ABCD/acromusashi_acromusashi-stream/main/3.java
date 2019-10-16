    public void success(TransactionAttempt tx)
    {
        DrpcRequestInfo requestInfo = (DrpcRequestInfo) this.idsMap.remove(tx);
        if (requestInfo != null)
        {
            ack(tx, requestInfo);
        }
    }
