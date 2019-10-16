    public void testTanhDefinition() {
        for (double x = 0.1; x < 1.2; x += 0.001) {
            SparseGradient sgX = SparseGradient.createVariable(0, x);
            SparseGradient tanh1 = sgX.exp().subtract(sgX.exp().reciprocal()).divide(sgX.exp().add(sgX.exp().reciprocal()));
            SparseGradient tanh2 = sgX.tanh();
            SparseGradient zero = tanh1.subtract(tanh2);
            checkF0F1(zero, 0.0, 0.0);
        }
    }
