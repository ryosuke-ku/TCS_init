    public void addInPlace(final SparseGradient a) {
        value += a.value;
        for (final Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            final int id = entry.getKey();
            final Double old = derivatives.get(id);
            if (old == null) {
                derivatives.put(id, entry.getValue());
            } else {
                derivatives.put(id, old + entry.getValue());
            }
        }
    }
