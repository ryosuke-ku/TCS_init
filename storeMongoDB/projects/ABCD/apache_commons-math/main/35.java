    public SparseGradient cbrt() {
        final double cbrt = FastMath.cbrt(value);
        return new SparseGradient(cbrt, 1.0 / (3 * cbrt * cbrt), derivatives);
    }
