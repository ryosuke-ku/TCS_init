    public double taylor(final double ... delta) {
        double y = value;
        for (int i = 0; i < delta.length; ++i) {
            y += delta[i] * getDerivative(i);
        }
        return y;
    }
