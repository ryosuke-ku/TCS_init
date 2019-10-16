    public void testCompose() {
        PolynomialFunction poly =
                new PolynomialFunction(new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 });
        for (double x = 0.1; x < 1.2; x += 0.001) {
            SparseGradient sgX = SparseGradient.createVariable(0, x);
            SparseGradient sgY1 = sgX.getField().getZero();
            for (int i = poly.degree(); i >= 0; --i) {
                sgY1 = sgY1.multiply(sgX).add(poly.getCoefficients()[i]);
            }
            SparseGradient sgY2 = sgX.compose(poly.value(x), poly.polynomialDerivative().value(x));
            SparseGradient zero = sgY1.subtract(sgY2);
            checkF0F1(zero, 0.0, 0.0);
        }
    }
