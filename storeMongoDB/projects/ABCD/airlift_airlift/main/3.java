    public double getMean()
    {
        List<Long> values = sample.values();

        if (!values.isEmpty()) {
            long sum = 0;
            for (long value : values) {
                sum += value;
            }

            return sum * 1.0 / values.size();
        }

        return Double.NaN;
    }
