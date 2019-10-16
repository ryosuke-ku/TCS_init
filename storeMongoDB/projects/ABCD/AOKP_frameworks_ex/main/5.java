    public int getPartitionForPosition(int position) {
        ensureCacheValid();
        int start = 0;
        for (int i = 0; i < mSize; i++) {
            int end = start + mPartitions[i].count;
            if (position >= start && position < end) {
                return i;
            }
            start = end;
        }
        return -1;
    }
