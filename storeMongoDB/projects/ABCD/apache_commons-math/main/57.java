    public SparseGradient acosh() {
        return new SparseGradient(FastMath.acosh(value), 1.0 / FastMath.sqrt(value * value - 1.0), derivatives);
    }
