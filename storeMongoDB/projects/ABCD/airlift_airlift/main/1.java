    public double getMin()
    {
        List<Long> values = sample.values();
        if (!values.isEmpty()) {
            return Collections.min(values);
        }

        return Double.NaN;
    }
