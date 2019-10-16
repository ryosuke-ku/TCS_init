    public SparseGradient acos() {
        return new SparseGradient(FastMath.acos(value), -1.0 / FastMath.sqrt(1 - value * value), derivatives);
    }
