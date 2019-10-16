    public SparseGradient asin() {
        return new SparseGradient(FastMath.asin(value), 1.0 / FastMath.sqrt(1 - value * value), derivatives);
    }
