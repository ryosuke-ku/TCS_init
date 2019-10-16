    private void removeRetentionPolicy(Path nodePath) {
        synchronized (retentionMap) {
            PathMap.Element<RetentionPolicyImpl> el =
                retentionMap.map(nodePath, true);
            if (el != null) {
                el.remove();
                retentionCnt--;
            } // else: no entry for holds on nodePath (should not occur)
        }
    }
