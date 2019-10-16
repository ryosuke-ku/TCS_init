    public SparseGradient sqrt() {
        final double sqrt = FastMath.sqrt(value);
        return new SparseGradient(sqrt, 0.5 / sqrt, derivatives);
    }
