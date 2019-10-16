    public SparseGradient asinh() {
        return new SparseGradient(FastMath.asinh(value), 1.0 / FastMath.sqrt(value * value + 1.0), derivatives);
    }
