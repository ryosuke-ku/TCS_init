    public void testSinhDefinition() {
        for (double x = 0.1; x < 1.2; x += 0.001) {
            SparseGradient sgX = SparseGradient.createVariable(0, x);
            SparseGradient sinh1 = sgX.exp().subtract(sgX.exp().reciprocal()).multiply(0.5);
            SparseGradient sinh2 = sgX.sinh();
            SparseGradient zero = sinh1.subtract(sinh2);
            checkF0F1(zero, 0.0, 0.0);
        }
    }
