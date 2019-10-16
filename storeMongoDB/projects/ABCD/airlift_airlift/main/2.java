    public double getMax()
    {
        List<Long> values = sample.values();
        if (!values.isEmpty()) {
            return Collections.max(values);
        }

        return Double.NaN;
    }
