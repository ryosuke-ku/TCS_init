    public SparseGradient compose(final double f0, final double f1) {
        return new SparseGradient(f0, f1, derivatives);
    }
