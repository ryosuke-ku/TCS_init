    public static SparseGradient pow(final double a, final SparseGradient x) {
        if (a == 0) {
            if (x.value == 0) {
                return x.compose(1.0, Double.NEGATIVE_INFINITY);
            } else if (x.value < 0) {
                return x.compose(Double.NaN, Double.NaN);
            } else {
                return x.getField().getZero();
            }
        } else {
            final double ax = FastMath.pow(a, x.value);
            return new SparseGradient(ax, ax * FastMath.log(a), x.derivatives);
        }
    }
