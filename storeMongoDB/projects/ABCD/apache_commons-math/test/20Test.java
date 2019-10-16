    public void testPowDoubleDS() {
        for (int maxOrder = 1; maxOrder < 5; ++maxOrder) {

            SparseGradient x = SparseGradient.createVariable(0, 0.1);
            SparseGradient y = SparseGradient.createVariable(1, 0.2);
            SparseGradient z = SparseGradient.createVariable(2, 0.3);
            List<SparseGradient> list = Arrays.asList(x, y, z,
                                                      x.add(y).add(z),
                                                      x.multiply(y).multiply(z));

            for (SparseGradient sg : list) {
                // the special case a = 0 is included here
                for (double a : new double[] { 0.0, 0.1, 1.0, 2.0, 5.0 }) {
                    SparseGradient reference = (a == 0) ?
                                               x.getField().getZero() :
                                               SparseGradient.createConstant(a).pow(sg);
                    SparseGradient result = SparseGradient.pow(a, sg);
                    Assert.assertEquals(reference, result);
                }

            }

            // negative base: -1^x can be evaluated for integers only, so value is sometimes OK, derivatives are always NaN
            SparseGradient negEvenInteger = SparseGradient.pow(-2.0, SparseGradient.createVariable(0, 2.0));
            Assert.assertEquals(4.0, negEvenInteger.getValue(), 1.0e-15);
            Assert.assertTrue(Double.isNaN(negEvenInteger.getDerivative(0)));
            SparseGradient negOddInteger = SparseGradient.pow(-2.0, SparseGradient.createVariable(0, 3.0));
            Assert.assertEquals(-8.0, negOddInteger.getValue(), 1.0e-15);
            Assert.assertTrue(Double.isNaN(negOddInteger.getDerivative(0)));
            SparseGradient negNonInteger = SparseGradient.pow(-2.0, SparseGradient.createVariable(0, 2.001));
            Assert.assertTrue(Double.isNaN(negNonInteger.getValue()));
            Assert.assertTrue(Double.isNaN(negNonInteger.getDerivative(0)));

            SparseGradient zeroNeg = SparseGradient.pow(0.0, SparseGradient.createVariable(0, -1.0));
            Assert.assertTrue(Double.isNaN(zeroNeg.getValue()));
            Assert.assertTrue(Double.isNaN(zeroNeg.getDerivative(0)));
            SparseGradient posNeg = SparseGradient.pow(2.0, SparseGradient.createVariable(0, -2.0));
            Assert.assertEquals(1.0 / 4.0, posNeg.getValue(), 1.0e-15);
            Assert.assertEquals(FastMath.log(2.0) / 4.0, posNeg.getDerivative(0), 1.0e-15);

            // very special case: a = 0 and power = 0
            SparseGradient zeroZero = SparseGradient.pow(0.0, SparseGradient.createVariable(0, 0.0));

            // this should be OK for simple first derivative with one variable only ...
            Assert.assertEquals(1.0, zeroZero.getValue(), 1.0e-15);
            Assert.assertEquals(Double.NEGATIVE_INFINITY, zeroZero.getDerivative(0), 1.0e-15);
            Assert.assertEquals(0.0, zeroZero.getDerivative(1), 1.0e-15);
            Assert.assertEquals(0.0, zeroZero.getDerivative(2), 1.0e-15);

        }

    }
