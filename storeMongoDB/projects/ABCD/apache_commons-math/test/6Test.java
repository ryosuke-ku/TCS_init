    public void testVarMultInPlace() {
        final double v1 = 1.0;
        final double c1 = 0.5;
        final double v2 = 2.0;
        final int id1 = -1;
        final int id2 = 3;
        final SparseGradient var1 = SparseGradient.createVariable(id1, v1);
        final SparseGradient sum = var1.multiply(c1);
        final SparseGradient mult = SparseGradient.createVariable(id2, v2);
        mult.multiplyInPlace(var1);
        sum.addInPlace(mult);
        Assert.assertEquals(v1 * c1 + v2 * v1, sum.getValue(), 1.0e-15); // returns the value
        Assert.assertEquals(2, sum.numVars());
        Assert.assertEquals(c1 + v2, sum.getDerivative(id1), 1.0e-15);
        Assert.assertEquals(v1, sum.getDerivative(id2), 1.0e-15);
    }
