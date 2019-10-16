    public SparseGradient copySign(final double sign) {
        final long m = Double.doubleToLongBits(value);
        final long s = Double.doubleToLongBits(sign);
        if ((m >= 0 && s >= 0) || (m < 0 && s < 0)) { // Sign is currently OK
            return this;
        }
        return negate(); // flip sign
    }
