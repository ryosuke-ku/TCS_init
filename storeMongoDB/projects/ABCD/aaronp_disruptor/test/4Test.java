    public void shouldThrowExceptionWhenIntervalsDoNotMatch()
    {
        Histogram histogram2 = new Histogram(new long[]{ 1L, 2L, 3L});
        histogram.addObservations(histogram2);
    }
