    public void testHypotNeglectible() {

        SparseGradient sgSmall = SparseGradient.createVariable(0, +3.0e-10);
        SparseGradient sgLarge = SparseGradient.createVariable(1, -4.0e25);

        Assert.assertEquals(sgLarge.abs().getValue(),
                            SparseGradient.hypot(sgSmall, sgLarge).getValue(),
                            1.0e-10);
        Assert.assertEquals(0,
                            SparseGradient.hypot(sgSmall, sgLarge).getDerivative(0),
                            1.0e-10);
        Assert.assertEquals(-1,
                            SparseGradient.hypot(sgSmall, sgLarge).getDerivative(1),
                            1.0e-10);

        Assert.assertEquals(sgLarge.abs().getValue(),
                            SparseGradient.hypot(sgLarge, sgSmall).getValue(),
                            1.0e-10);
        Assert.assertEquals(0,
                            SparseGradient.hypot(sgLarge, sgSmall).getDerivative(0),
                            1.0e-10);
        Assert.assertEquals(-1,
                            SparseGradient.hypot(sgLarge, sgSmall).getDerivative(1),
                            1.0e-10);

    }
