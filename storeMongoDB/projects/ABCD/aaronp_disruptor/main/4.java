    public void addObservations(final Histogram histogram)
    {
        if (upperBounds.length != histogram.upperBounds.length)
        {
            throw new IllegalArgumentException("Histograms must have matching intervals");
        }

        for (int i = 0, size = upperBounds.length; i < size; i++)
        {
            if (upperBounds[i] != histogram.upperBounds[i])
            {
                throw new IllegalArgumentException("Histograms must have matching intervals");
            }
        }

        for (int i = 0, size = counts.length; i < size; i++)
        {
            counts[i] += histogram.counts[i];
        }

        trackRange(histogram.minValue);
        trackRange(histogram.maxValue);
    }
