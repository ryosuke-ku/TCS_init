    public SparseGradient log1p() {
        return new SparseGradient(FastMath.log1p(value), 1.0 / (1.0 + value), derivatives);
    }
