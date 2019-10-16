    public void shouldCountPositiveNumbers() {
        int positives = new NumberCruncher(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4).countPositive();
        assertThat(positives, is(5));
    }
