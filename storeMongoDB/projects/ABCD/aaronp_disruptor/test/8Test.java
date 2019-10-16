    public void shouldGetMaxObservation()
    {
        addObservations(histogram, 1L, 7L, 10L, 10L, 11L, 144L);

        assertThat(Long.valueOf(histogram.getMax()), is(Long.valueOf(144L)));
    }
