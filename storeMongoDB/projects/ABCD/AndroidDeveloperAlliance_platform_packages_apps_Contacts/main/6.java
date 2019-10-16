    public View getView(int position, View convertView, ViewGroup parent) {
        ensureCacheValid();
        int start = 0;
        for (int i = 0; i < mCounts.length; i++) {
            int end = start + mCounts[i];
            if (position >= start && position < end) {
                return mAdapters[i].getView(position - start, convertView, parent);
            }
            start = end;
        }

        throw new ArrayIndexOutOfBoundsException(position);
    }
