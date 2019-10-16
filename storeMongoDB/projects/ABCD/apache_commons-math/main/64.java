    public SparseGradient linearCombination(final double a1, final SparseGradient b1,
                                              final double a2, final SparseGradient b2,
                                              final double a3, final SparseGradient b3,
                                              final double a4, final SparseGradient b4) {

        // compute a simple value, with all partial derivatives
        SparseGradient out = b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3)).add(b4.multiply(a4));

        // recompute an accurate value, taking care of cancellations
        out.value = MathArrays.linearCombination(a1, b1.value,
                                                 a2, b2.value,
                                                 a3, b3.value,
                                                 a4, b4.value);

        return out;

    }
