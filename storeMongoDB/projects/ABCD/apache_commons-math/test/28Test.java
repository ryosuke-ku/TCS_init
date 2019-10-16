    public void testSignum() {

        SparseGradient minusOne = SparseGradient.createVariable(0, -1.0);
        Assert.assertEquals(-1.0, minusOne.signum().getValue(), 1.0e-15);
        Assert.assertEquals( 0.0, minusOne.signum().getDerivative(0), 1.0e-15);

        SparseGradient plusOne = SparseGradient.createVariable(0, +1.0);
        Assert.assertEquals(+1.0, plusOne.signum().getValue(), 1.0e-15);
        Assert.assertEquals( 0.0, plusOne.signum().getDerivative(0), 1.0e-15);

        SparseGradient minusZero = SparseGradient.createVariable(0, -0.0);
        Assert.assertEquals(-0.0, minusZero.signum().getValue(), 1.0e-15);
        Assert.assertTrue(Double.doubleToLongBits(minusZero.signum().getValue()) < 0);
        Assert.assertEquals( 0.0, minusZero.signum().getDerivative(0), 1.0e-15);

        SparseGradient plusZero = SparseGradient.createVariable(0, +0.0);
        Assert.assertEquals(+0.0, plusZero.signum().getValue(), 1.0e-15);
        Assert.assertTrue(Double.doubleToLongBits(plusZero.signum().getValue()) == 0);
        Assert.assertEquals( 0.0, plusZero.signum().getDerivative(0), 1.0e-15);

    }
