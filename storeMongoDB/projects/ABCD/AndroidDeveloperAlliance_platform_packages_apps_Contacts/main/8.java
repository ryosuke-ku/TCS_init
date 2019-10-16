    public boolean isEnabled(int position) {
        ensureCacheValid();
        int start = 0;
        for (int i = 0; i < mCounts.length; i++) {
            int end = start + mCounts[i];
            if (position >= start && position < end) {
                return mAdapters[i].areAllItemsEnabled()
                        || mAdapters[i].isEnabled(position - start);
            }
            start = end;
        }

        throw new ArrayIndexOutOfBoundsException(position);
    }
