    public SparseGradient signum() {
        return createConstant(FastMath.signum(value));
    }
