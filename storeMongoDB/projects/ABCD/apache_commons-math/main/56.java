    public SparseGradient tanh() {
        final double t = FastMath.tanh(value);
        return new SparseGradient(t, 1 - t * t, derivatives);
    }
