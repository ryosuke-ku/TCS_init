    public void testToDegreesDefinition() {
        double epsilon = 3.0e-16;
        for (int maxOrder = 0; maxOrder < 6; ++maxOrder) {
            for (double x = 0.1; x < 1.2; x += 0.001) {
                SparseGradient sgX = SparseGradient.createVariable(0, x);
                Assert.assertEquals(FastMath.toDegrees(x), sgX.toDegrees().getValue(), epsilon);
                Assert.assertEquals(180 / FastMath.PI, sgX.toDegrees().getDerivative(0), epsilon);
            }
        }
    }
