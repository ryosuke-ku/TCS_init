    public void testHypotNoOverflow() {

        SparseGradient sgX = SparseGradient.createVariable(0, +3.0e250);
        SparseGradient sgY = SparseGradient.createVariable(1, -4.0e250);
        SparseGradient hypot = SparseGradient.hypot(sgX, sgY);
        Assert.assertEquals(5.0e250, hypot.getValue(), 1.0e235);
        Assert.assertEquals(sgX.getValue() / hypot.getValue(), hypot.getDerivative(0), 1.0e-10);
        Assert.assertEquals(sgY.getValue() / hypot.getValue(), hypot.getDerivative(1), 1.0e-10);

        SparseGradient sqrt  = sgX.multiply(sgX).add(sgY.multiply(sgY)).sqrt();
        Assert.assertTrue(Double.isInfinite(sqrt.getValue()));

    }
