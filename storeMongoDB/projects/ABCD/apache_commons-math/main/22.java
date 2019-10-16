    public SparseGradient remainder(final SparseGradient a) {

        // compute k such that lhs % rhs = lhs - k rhs
        final double rem = FastMath.IEEEremainder(value, a.value);
        final double k   = FastMath.rint((value - rem) / a.value);

        return subtract(a.multiply(k));

    }
