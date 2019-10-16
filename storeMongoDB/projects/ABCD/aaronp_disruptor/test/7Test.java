    public void shouldGetMinObservation()
    {
        addObservations(histogram, 1L, 7L, 10L, 10L, 11L, 144L);

        assertThat(Long.valueOf(histogram.getMin()), is(Long.valueOf(1L)));
    }
