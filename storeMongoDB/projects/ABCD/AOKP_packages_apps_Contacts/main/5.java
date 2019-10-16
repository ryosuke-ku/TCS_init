    public int getItemViewType(int position) {
        ensureCacheValid();
        int start = 0;
        int viewTypeOffset = 0;
        for (int i = 0; i < mCounts.length; i++) {
            int end = start + mCounts[i];
            if (position >= start && position < end) {
                return viewTypeOffset + mAdapters[i].getItemViewType(position - start);
            }
            viewTypeOffset += mViewTypeCounts[i];
            start = end;
        }

        throw new ArrayIndexOutOfBoundsException(position);
    }
