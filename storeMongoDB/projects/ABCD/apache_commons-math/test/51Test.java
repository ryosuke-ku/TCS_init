    public void testAtan2() {
        for (double x = -1.7; x < 2; x += 0.2) {
            SparseGradient sgX = SparseGradient.createVariable(0, x);
            for (double y = -1.7; y < 2; y += 0.2) {
                SparseGradient sgY = SparseGradient.createVariable(1, y);
                SparseGradient atan2 = SparseGradient.atan2(sgY, sgX);
                SparseGradient ref = sgY.divide(sgX).atan();
                if (x < 0) {
                    ref = (y < 0) ? ref.subtract(FastMath.PI) : ref.add(FastMath.PI);
                }
                SparseGradient zero = atan2.subtract(ref);
                checkF0F1(zero, 0.0, 0.0);
            }
        }
    }
