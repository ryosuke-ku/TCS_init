    public SparseGradient toRadians() {
        return new SparseGradient(FastMath.toRadians(value), FastMath.toRadians(1.0), derivatives);
    }
