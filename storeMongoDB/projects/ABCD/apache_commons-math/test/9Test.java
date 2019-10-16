    public void testTangentDefinition() {
        for (double x = 0.1; x < 1.2; x += 0.001) {
            SparseGradient sgX = SparseGradient.createVariable(0, x);
            SparseGradient tan1 = sgX.sin().divide(sgX.cos());
            SparseGradient tan2 = sgX.tan();
            SparseGradient zero = tan1.subtract(tan2);
            checkF0F1(zero, 0.0, 0.0);
        }
    }
