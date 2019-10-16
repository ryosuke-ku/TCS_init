    public SparseGradient cos() {
        return new SparseGradient(FastMath.cos(value), -FastMath.sin(value), derivatives);
    }
