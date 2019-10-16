    public void testNegate() {
        checkF0F1(SparseGradient.createVariable(0, 1.0).negate(), -1.0, -1.0, 0.0, 0.0);
        checkF0F1(SparseGradient.createVariable(1, 2.0).negate(), -2.0, 0.0, -1.0, 0.0);
        checkF0F1(SparseGradient.createVariable(2, 3.0).negate(), -3.0, 0.0, 0.0, -1.0);
    }
