    public void testRootNSingularity() {
        for (int n = 2; n < 10; ++n) {
            SparseGradient sgZero = SparseGradient.createVariable(0, 0.0);
            SparseGradient rootN  = sgZero.rootN(n);
            Assert.assertEquals(0.0, rootN.getValue(), 1.0e-5);
            Assert.assertTrue(Double.isInfinite(rootN.getDerivative(0)));
            Assert.assertTrue(rootN.getDerivative(0) > 0);
        }

    }
