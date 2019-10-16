    public SparseGradient reciprocal() {
        return new SparseGradient(1.0 / value, -1.0 / (value * value), derivatives);
    }
