   public void testDerivativeComparison() {
       final UnivariateDifferentiableFunction sPrime = new Sqrt();
       final UnivariateFunction f = new UnivariateFunction() {
               @Override
            public double value(double x) {
                   return 1 / (2 * FastMath.sqrt(x));
               }
           };

       for (double x = 1e-30; x < 1e10; x *= 2) {
           final double fX = f.value(x);
           final double sX = sPrime.value(new DerivativeStructure(1, 1, 0, x)).getPartialDerivative(1);
           Assert.assertEquals("x=" + x, fX, sX, FastMath.ulp(fX));
       }
   }
