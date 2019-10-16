    public SparseGradient add(final double c) {
        final SparseGradient out = new SparseGradient(value + c, derivatives);
        return out;
    }
