    public void testHeaviside() {
        final UnivariateFunction h = new StepFunction(new double[] {-1, 0},
                                                          new double[] {0, 1});

        Assert.assertEquals(0, h.value(Double.NEGATIVE_INFINITY), 0);
        Assert.assertEquals(0, h.value(-Double.MAX_VALUE), 0);
        Assert.assertEquals(0, h.value(-2), 0);
        Assert.assertEquals(0, h.value(-Double.MIN_VALUE), 0);
        Assert.assertEquals(1, h.value(0), 0);
        Assert.assertEquals(1, h.value(2), 0);
        Assert.assertEquals(1, h.value(Double.POSITIVE_INFINITY), 0);
    }
