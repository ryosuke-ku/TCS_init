    public void shouldCorrectMeanForSkewInTopAndBottomPopulatedIntervals()
    {
        final long[] INTERVALS = new long[]{ 100, 110, 120, 130, 140, 150, 1000, 10000 };
        final Histogram histogram = new Histogram(INTERVALS);

        for (long i = 100; i < 152; i++)
        {
            histogram.addObservation(i);
        }

        assertThat(histogram.getMean(), is(new BigDecimal("125.02")));
    }
