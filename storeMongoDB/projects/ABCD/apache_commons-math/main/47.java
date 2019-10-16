    public SparseGradient sin() {
        return new SparseGradient(FastMath.sin(value), FastMath.cos(value), derivatives);
    }
