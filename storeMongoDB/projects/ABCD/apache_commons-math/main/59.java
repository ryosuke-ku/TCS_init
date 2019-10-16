    public SparseGradient atanh() {
        return new SparseGradient(FastMath.atanh(value), 1.0 / (1.0 - value * value), derivatives);
    }
