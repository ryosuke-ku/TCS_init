    public void testCosAcos() {
        for (double x = 0.1; x < 1.2; x += 0.001) {
            SparseGradient sgX = SparseGradient.createVariable(0, x);
            SparseGradient rebuiltX = sgX.cos().acos();
            SparseGradient zero = rebuiltX.subtract(sgX);
            checkF0F1(zero, 0.0, 0.0);
        }
    }
