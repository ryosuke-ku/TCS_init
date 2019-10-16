    public SparseGradient atan() {
        return new SparseGradient(FastMath.atan(value), 1.0 / (1 + value * value), derivatives);
    }
