    public void multiplyInPlace(final SparseGradient a) {
        // Derivatives.
        for (Map.Entry<Integer, Double> entry : derivatives.entrySet()) {
            derivatives.put(entry.getKey(), a.value * entry.getValue());
        }
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            final int id = entry.getKey();
            final Double old = derivatives.get(id);
            if (old == null) {
                derivatives.put(id, value * entry.getValue());
            } else {
                derivatives.put(id, old + value * entry.getValue());
            }
        }
        value *= a.value;
    }
