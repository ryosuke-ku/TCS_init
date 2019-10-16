    public SparseGradient divide(final double c) {
        return new SparseGradient(value / c, 1.0 / c, derivatives);
    }
