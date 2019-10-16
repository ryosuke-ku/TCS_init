    public SparseGradient rootN(final int n) {
        if (n == 2) {
            return sqrt();
        } else if (n == 3) {
            return cbrt();
        } else {
            final double root = FastMath.pow(value, 1.0 / n);
            return new SparseGradient(root, 1.0 / (n * FastMath.pow(root, n - 1)), derivatives);
        }
    }
