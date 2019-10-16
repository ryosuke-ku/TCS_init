    public void shouldGetTwoNinesUpperBound()
    {
        final long[] INTERVALS = new long[]{ 1, 10, 100, 1000, 10000 };
        final Histogram histogram = new Histogram(INTERVALS);

        for (long i = 1; i < 101; i++)
        {
            histogram.addObservation(i);
        }

        assertThat(Long.valueOf(histogram.getTwoNinesUpperBound()), is(Long.valueOf(100L)));
    }
