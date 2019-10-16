    public int getOffsetInPartition(int position) {
        ensureCacheValid();
        int start = 0;
        for (int i = 0; i < mSize; i++) {
            int end = start + mPartitions[i].count;
            if (position >= start && position < end) {
                int offset = position - start;
                if (mPartitions[i].hasHeader) {
                    offset--;
                }
                return offset;
            }
            start = end;
        }
        return -1;
    }
