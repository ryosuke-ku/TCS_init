    public synchronized String[] getExcludedFiles() {
        slowScan();
        return filesExcluded.toArray(new String[filesExcluded.size()]);
    }
