    protected void tearDown() throws Exception {        
        try {
            Hold[] hs = retentionMgr.getHolds(childNPath);
            for (int i = 0; i < hs.length; i++) {
                retentionMgr.removeHold(childNPath, hs[i]);
            }
            superuser.save();
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        try {
            if (retentionMgr.getRetentionPolicy(childNPath) != null) {
                retentionMgr.removeRetentionPolicy(childNPath);
            }
            superuser.save();
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        super.tearDown();
    }
