    public void testCeilFloorRintLong() {

        SparseGradient x = SparseGradient.createVariable(0, -1.5);
        Assert.assertEquals(-1.5, x.getValue(), 1.0e-15);
        Assert.assertEquals(+1.0, x.getDerivative(0), 1.0e-15);
        Assert.assertEquals(-1.0, x.ceil().getValue(), 1.0e-15);
        Assert.assertEquals(+0.0, x.ceil().getDerivative(0), 1.0e-15);
        Assert.assertEquals(-2.0, x.floor().getValue(), 1.0e-15);
        Assert.assertEquals(+0.0, x.floor().getDerivative(0), 1.0e-15);
        Assert.assertEquals(-2.0, x.rint().getValue(), 1.0e-15);
        Assert.assertEquals(+0.0, x.rint().getDerivative(0), 1.0e-15);
        Assert.assertEquals(-2.0, x.subtract(x.getField().getOne()).rint().getValue(), 1.0e-15);
        Assert.assertEquals(-1l, x.round(), 1.0e-15);

    }
