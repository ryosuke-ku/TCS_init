    /*package*/ void addAdapter(ListAdapter adapter) {
        if (mSize >= mAdapters.length) {
            int newCapacity = mSize + 2;
            ListAdapter[] newAdapters = new ListAdapter[newCapacity];
            System.arraycopy(mAdapters, 0, newAdapters, 0, mSize);
            mAdapters = newAdapters;

            int[] newCounts = new int[newCapacity];
            System.arraycopy(mCounts, 0, newCounts, 0, mSize);
            mCounts = newCounts;

            int[] newViewTypeCounts = new int[newCapacity];
            System.arraycopy(mViewTypeCounts, 0, newViewTypeCounts, 0, mSize);
            mViewTypeCounts = newViewTypeCounts;
        }

        adapter.registerDataSetObserver(mDataSetObserver);

        int count = adapter.getCount();
        int viewTypeCount = adapter.getViewTypeCount();

        mAdapters[mSize] = adapter;
        mCounts[mSize] = count;
        mCount += count;
        mAllItemsEnabled &= adapter.areAllItemsEnabled();
        mViewTypeCounts[mSize] = viewTypeCount;
        mViewTypeCount += viewTypeCount;
        mSize++;

        notifyDataChanged();
    }
