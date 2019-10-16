    public void shouldClearCounts()
    {
        addObservations(histogram, 1L, 7L, 10L, 3000L);
        histogram.clear();

        for (int i = 0, size = histogram.getSize(); i < size; i++)
        {
            assertThat(Long.valueOf(histogram.getCountAt(i)), is(Long.valueOf(0)));
        }
    }
