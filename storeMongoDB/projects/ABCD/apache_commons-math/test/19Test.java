    public void testField() {
            SparseGradient x = SparseGradient.createVariable(0, 1.0);
            checkF0F1(x.getField().getZero(), 0.0, 0.0, 0.0, 0.0);
            checkF0F1(x.getField().getOne(), 1.0, 0.0, 0.0, 0.0);
            Assert.assertEquals(SparseGradient.class, x.getField().getRuntimeClass());
    }
