    public int compareTo(ColumnIdentifier that)
    {
        int c = Long.compare(this.prefixComparison, that.prefixComparison);
        if (c != 0)
            return c;
        if (this == that)
            return 0;
        return ByteBufferUtil.compareUnsigned(this.bytes, that.bytes);
    }
