    public void shouldCountTotalObservations()
    {
        addObservations(histogram, 1L, 7L, 10L, 3000L);

        assertThat(Long.valueOf(histogram.getCount()), is(Long.valueOf(4L)));
    }
