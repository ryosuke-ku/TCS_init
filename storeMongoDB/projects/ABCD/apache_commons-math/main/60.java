    public SparseGradient toDegrees() {
        return new SparseGradient(FastMath.toDegrees(value), FastMath.toDegrees(1.0), derivatives);
    }
