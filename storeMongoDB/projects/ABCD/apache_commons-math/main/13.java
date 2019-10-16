    public SparseGradient multiply(final int n) {
        return new SparseGradient(value * n, n, derivatives);
    }
