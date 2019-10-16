    /* package */ void closePendingStatements() {
        if (!isOpen()) {
            // since this database is already closed, no need to finalize anything.
            mClosedStatementIds.clear();
            return;
        }
        verifyLockOwner();
        /* to minimize synchronization on mClosedStatementIds, make a copy of the list */
        ArrayList<Integer> list = new ArrayList<Integer>(mClosedStatementIds.size());
        synchronized(mClosedStatementIds) {
            list.addAll(mClosedStatementIds);
            mClosedStatementIds.clear();
        }
        // finalize all the statements from the copied list
        int size = list.size();
        for (int i = 0; i < size; i++) {
            native_finalize(list.get(i));
        }
    }
