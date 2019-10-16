    public Partition getPartition(int partitionIndex) {
        if (partitionIndex >= mSize) {
            throw new ArrayIndexOutOfBoundsException(partitionIndex);
        }
        return mPartitions[partitionIndex];
    }
