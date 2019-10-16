    public void testAdd() {
        SparseGradient x = SparseGradient.createVariable(0, 1.0);
        SparseGradient y = SparseGradient.createVariable(1, 2.0);
        SparseGradient z = SparseGradient.createVariable(2, 3.0);
        SparseGradient xyz = x.add(y.add(z));
        checkF0F1(xyz, x.getValue() + y.getValue() + z.getValue(), 1.0, 1.0, 1.0);
    }
