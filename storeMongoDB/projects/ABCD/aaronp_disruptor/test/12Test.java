    public void shouldToString()
    {
        addObservations(histogram, 1L, 7L, 10L, 300L);

        String expectedResults = "Histogram{min=1, max=300, mean=53.25, 99%=1000, 99.99%=1000, [1=1, 10=2, 100=0, 1000=1, 9223372036854775807=0]}";
        assertThat(histogram.toString(), is(expectedResults));
    }
