    public SparseGradient subtract(double c) {
        return new SparseGradient(value - c, derivatives);
    }
