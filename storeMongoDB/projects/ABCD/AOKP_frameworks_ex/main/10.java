    public boolean areAllItemsEnabled() {
        for (int i = 0; i < mSize; i++) {
            if (mPartitions[i].hasHeader) {
                return false;
            }
        }
        return true;
    }
