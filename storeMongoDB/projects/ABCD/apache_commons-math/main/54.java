    public SparseGradient cosh() {
        return new SparseGradient(FastMath.cosh(value), FastMath.sinh(value), derivatives);
    }
