    public void emptyStringMakesEmptyMap() {
        final String empty = "";
        ValueObjectMap map = VdsStatic.PmOptionsStringToMap(empty);
        mapIsEmpty(map);
    }
ride
            public Object runInTransaction() {
                try {
                    outerTransaction = tm.getTransaction();
                } catch (SystemException e) {
                    e.printStackTrace();
                }
                validateStatus(Status.STATUS_ACTIVE, outerTransaction);
                // open a new transaction
                TransactionSupport.executeInNewTransaction(new TransactionMethod<Object>() {
                    @Override
                    public Object runInTransaction() {
                        try {
                            innerTransaction = tm.getTransaction();
                            innerTransaction.setRollbackOnly();
                        } catch (SystemException e) {
                            e.printStackTrace();
                        }
                        // validate that the inner and outer
                        // transactions are not equals
                        Assert.assertFalse(outerTransaction.equals(innerTransaction));
                        validateStatus(Status.STATUS_ACTIVE, outerTransaction);
                        validateStatus(Status.STATUS_MARKED_ROLLBACK, innerTransaction);
                        return null;

                    }
                });
                validateStatus(Status.STATUS_ACTIVE, outerTransaction);
                validateStatus(Status.STATUS_ROLLEDBACK, innerTransaction);
                return null;
            }
        });
        validateStatus(Status.STATUS_COMMITTED, outerTransaction);
    }
