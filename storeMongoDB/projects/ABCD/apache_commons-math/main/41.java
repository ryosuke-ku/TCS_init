    public SparseGradient exp() {
        final double e = FastMath.exp(value);
        return new SparseGradient(e, e, derivatives);
    }
