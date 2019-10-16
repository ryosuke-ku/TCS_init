    public double getPercentile(double percentile)
    {
        if (percentile < 0 || percentile > 1) {
            throw new IllegalArgumentException("percentile must be between 0 and 1");
        }
        return sample.percentiles(percentile)[0];
    }
