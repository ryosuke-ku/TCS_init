    public void shouldWalkIntervals()
    {
        for (int i = 0, size = histogram.getSize(); i < size; i++)
        {
            assertThat(Long.valueOf(histogram.getUpperBoundAt(i)), is(Long.valueOf(INTERVALS[i])));
        }
    }
