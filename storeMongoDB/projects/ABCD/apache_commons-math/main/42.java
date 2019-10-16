    public SparseGradient expm1() {
        return new SparseGradient(FastMath.expm1(value), FastMath.exp(value), derivatives);
    }
