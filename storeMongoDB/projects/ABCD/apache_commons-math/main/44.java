    public SparseGradient log10() {
        return new SparseGradient(FastMath.log10(value), 1.0 / (FastMath.log(10.0) * value), derivatives);
    }
