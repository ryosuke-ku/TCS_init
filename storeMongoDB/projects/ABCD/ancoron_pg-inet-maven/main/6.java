    public IPTarget add(long offset) {
        // we use 'valueOf' to benefit from internal caching...
        return add(BigInteger.valueOf(offset));
    }
