    public synchronized String[] getExcludedDirectories() {
        slowScan();
        return dirsExcluded.toArray(new String[dirsExcluded.size()]);
    }
