    public void testPrimitiveRemainder() {
        for (double x = -1.7; x < 2; x += 0.2) {
            SparseGradient sgX = SparseGradient.createVariable(0, x);
            for (double y = -1.7; y < 2; y += 0.2) {
                SparseGradient remainder = sgX.remainder(y);
                SparseGradient ref = sgX.subtract(x - FastMath.IEEEremainder(x, y));
                SparseGradient zero = remainder.subtract(ref);
                checkF0F1(zero, 0.0, 0.0, 0.0);
            }
        }
    }
