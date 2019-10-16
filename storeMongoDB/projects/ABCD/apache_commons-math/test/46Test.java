    public void testCoshDefinition() {
        for (double x = 0.1; x < 1.2; x += 0.001) {
            SparseGradient sgX = SparseGradient.createVariable(0, x);
            SparseGradient cosh1 = sgX.exp().add(sgX.exp().reciprocal()).multiply(0.5);
            SparseGradient cosh2 = sgX.cosh();
            SparseGradient zero = cosh1.subtract(cosh2);
            checkF0F1(zero, 0.0, 0.0);
        }
    }
