    public void testRandomPrintRange() {
        final int expectedMinLengthInclusive = 1;
        final int expectedMaxLengthExclusive = 11;
        final String pattern = "^\\p{Print}{" + expectedMinLengthInclusive + ',' + expectedMaxLengthExclusive + "}$";

        int maxCreatedLength = expectedMinLengthInclusive;
        int minCreatedLength = expectedMaxLengthExclusive - 1;
        for (int i = 0; i < 1000; i++) {
            final String s = RandomStringUtils.randomPrint(expectedMinLengthInclusive, expectedMaxLengthExclusive);
            assertThat("within range", s.length(), allOf(greaterThanOrEqualTo(expectedMinLengthInclusive), lessThanOrEqualTo(expectedMaxLengthExclusive - 1)));
            assertTrue(s, s.matches(pattern));

            if (s.length() < minCreatedLength) {
                minCreatedLength = s.length();
            }

            if (s.length() > maxCreatedLength) {
                maxCreatedLength = s.length();
            }
        }
        assertThat("min generated, may fail randomly rarely", minCreatedLength, is(expectedMinLengthInclusive));
        assertThat("max generated, may fail randomly rarely", maxCreatedLength, is(expectedMaxLengthExclusive - 1));
    }
