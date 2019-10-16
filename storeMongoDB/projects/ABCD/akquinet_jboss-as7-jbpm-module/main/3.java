    public void rollback(boolean transactionOwner) {
        try {
        	if (transactionOwner) {
        		this.ut.rollback();
        	} else {
        		this.ut.setRollbackOnly();
        	}
        } catch ( Exception e ) {
            logger.warn( "Unable to rollback transaction", e);
            throw new RuntimeException( "Unable to rollback transaction",
                                        e );
        }
    }
