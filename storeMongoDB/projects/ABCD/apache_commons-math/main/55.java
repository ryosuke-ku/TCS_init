    public SparseGradient sinh() {
        return new SparseGradient(FastMath.sinh(value), FastMath.cosh(value), derivatives);
    }
