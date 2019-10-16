    public SparseGradient log() {
        return new SparseGradient(FastMath.log(value), 1.0 / value, derivatives);
    }
