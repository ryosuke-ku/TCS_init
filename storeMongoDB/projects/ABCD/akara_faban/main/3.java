    public void removeRun(String runId) {
        wlock.lock();
        try {
            HashSet<Entry> removeSet = runEntries.get(runId);
            if (removeSet != null) {
                for (Entry entry : removeSet) {
                    entry.runIds.remove(runId);
                }
            }
            runEntries.remove(runId);
        } finally {
            wlock.unlock();
        }
    }
