    public SparseGradient tan() {
        final double t = FastMath.tan(value);
        return new SparseGradient(t, 1 + t * t, derivatives);
    }
