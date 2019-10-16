    public void testLinearCombination2DoubleDS() {
        // we compare accurate versus naive dot product implementations
        // on regular vectors (i.e. not extreme cases like in the previous test)
        UniformRandomProvider random = RandomSource.create(RandomSource.WELL_1024_A,
                                                           0xc6af886975069f11l);

        for (int i = 0; i < 10000; ++i) {
            final double[] u = new double[4];
            final SparseGradient[] v = new SparseGradient[4];
            for (int j = 0; j < u.length; ++j) {
                u[j] = 1e17 * random.nextDouble();
                v[j] = SparseGradient.createVariable(j, 1e17 * random.nextDouble());
            }

            SparseGradient lin = v[0].linearCombination(u[0], v[0], u[1], v[1]);
            double ref = u[0] * v[0].getValue() +
                         u[1] * v[1].getValue();
            Assert.assertEquals(ref, lin.getValue(), 1.0e-15 * FastMath.abs(ref));
            Assert.assertEquals(u[0], lin.getDerivative(0), 1.0e-15 * FastMath.abs(v[0].getValue()));
            Assert.assertEquals(u[1], lin.getDerivative(1), 1.0e-15 * FastMath.abs(v[1].getValue()));

            lin = v[0].linearCombination(u[0], v[0], u[1], v[1], u[2], v[2]);
            ref = u[0] * v[0].getValue() +
                  u[1] * v[1].getValue() +
                  u[2] * v[2].getValue();
            Assert.assertEquals(ref, lin.getValue(), 1.0e-15 * FastMath.abs(ref));
            Assert.assertEquals(u[0], lin.getDerivative(0), 1.0e-15 * FastMath.abs(v[0].getValue()));
            Assert.assertEquals(u[1], lin.getDerivative(1), 1.0e-15 * FastMath.abs(v[1].getValue()));
            Assert.assertEquals(u[2], lin.getDerivative(2), 1.0e-15 * FastMath.abs(v[2].getValue()));

            lin = v[0].linearCombination(u[0], v[0], u[1], v[1], u[2], v[2], u[3], v[3]);
            ref = u[0] * v[0].getValue() +
                  u[1] * v[1].getValue() +
                  u[2] * v[2].getValue() +
                  u[3] * v[3].getValue();
            Assert.assertEquals(ref, lin.getValue(), 1.0e-15 * FastMath.abs(ref));
            Assert.assertEquals(u[0], lin.getDerivative(0), 1.0e-15 * FastMath.abs(v[0].getValue()));
            Assert.assertEquals(u[1], lin.getDerivative(1), 1.0e-15 * FastMath.abs(v[1].getValue()));
            Assert.assertEquals(u[2], lin.getDerivative(2), 1.0e-15 * FastMath.abs(v[2].getValue()));
            Assert.assertEquals(u[3], lin.getDerivative(3), 1.0e-15 * FastMath.abs(v[3].getValue()));

        }
    }
