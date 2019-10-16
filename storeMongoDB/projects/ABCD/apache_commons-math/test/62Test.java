    public void testTaylorPolynomial() {
        for (double x = 0; x < 1.2; x += 0.1) {
            SparseGradient sgX = SparseGradient.createVariable(0, x);
            for (double y = 0; y < 1.2; y += 0.2) {
                SparseGradient sgY = SparseGradient.createVariable(1, y);
                for (double z = 0; z < 1.2; z += 0.2) {
                    SparseGradient sgZ = SparseGradient.createVariable(2, z);
                    SparseGradient f = sgX.multiply(3).add(sgZ.multiply(-2)).add(sgY.multiply(5));
                    for (double dx = -0.2; dx < 0.2; dx += 0.2) {
                        for (double dy = -0.2; dy < 0.2; dy += 0.1) {
                            for (double dz = -0.2; dz < 0.2; dz += 0.1) {
                                double ref = 3 * (x + dx) + 5 * (y + dy) -2 * (z + dz);
                                Assert.assertEquals(ref, f.taylor(dx, dy, dz), 3.0e-15);
                            }
                        }
                    }
                }
            }
        }
    }
