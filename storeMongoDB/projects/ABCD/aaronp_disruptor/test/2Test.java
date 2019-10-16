    public void shouldConfirmIntervalsAreInitialised()
    {
        for (int i = 0, size = histogram.getSize(); i < size; i++)
        {
            assertThat(Long.valueOf(histogram.getCountAt(i)), is(Long.valueOf(0L)));
        }
    }
