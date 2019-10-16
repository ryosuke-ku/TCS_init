    public SparseGradient negate() {
        return new SparseGradient(-value, -1.0, derivatives);
    }
