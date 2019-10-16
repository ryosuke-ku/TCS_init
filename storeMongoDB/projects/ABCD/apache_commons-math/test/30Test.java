    public void testAtan2SpecialCases() {

        SparseGradient pp =
                SparseGradient.atan2(SparseGradient.createVariable(1, +0.0),
                                          SparseGradient.createVariable(1, +0.0));
        Assert.assertEquals(0, pp.getValue(), 1.0e-15);
        Assert.assertEquals(+1, FastMath.copySign(1, pp.getValue()), 1.0e-15);

        SparseGradient pn =
                SparseGradient.atan2(SparseGradient.createVariable(1, +0.0),
                                          SparseGradient.createVariable(1, -0.0));
        Assert.assertEquals(FastMath.PI, pn.getValue(), 1.0e-15);

        SparseGradient np =
                SparseGradient.atan2(SparseGradient.createVariable(1, -0.0),
                                          SparseGradient.createVariable(1, +0.0));
        Assert.assertEquals(0, np.getValue(), 1.0e-15);
        Assert.assertEquals(-1, FastMath.copySign(1, np.getValue()), 1.0e-15);

        SparseGradient nn =
                SparseGradient.atan2(SparseGradient.createVariable(1, -0.0),
                                          SparseGradient.createVariable(1, -0.0));
        Assert.assertEquals(-FastMath.PI, nn.getValue(), 1.0e-15);

    }
