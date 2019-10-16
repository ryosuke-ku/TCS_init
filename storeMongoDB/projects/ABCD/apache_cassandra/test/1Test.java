    public void testFromStringWithIso8601AlternativePattern()
    {
        assertEquals(Duration.newInstance(12, 2, 0), Duration.from("P0001-00-02T00:00:00"));
        assertEquals(Duration.newInstance(14, 0, 0), Duration.from("P0001-02-00T00:00:00"));
        assertEquals(Duration.newInstance(12, 0, 2 * NANOS_PER_HOUR), Duration.from("P0001-00-00T02:00:00"));
        assertEquals(Duration.newInstance(-14, 0, 0), Duration.from("-P0001-02-00T00:00:00"));
        assertEquals(Duration.newInstance(0, 2, 0), Duration.from("P0000-00-02T00:00:00"));
        assertEquals(Duration.newInstance(0, 0, 30 * NANOS_PER_HOUR), Duration.from("P0000-00-00T30:00:00"));
        assertEquals(Duration.newInstance(0, 0, 30 * NANOS_PER_HOUR + 20 * NANOS_PER_MINUTE), Duration.from("P0000-00-00T30:20:00"));
        assertEquals(Duration.newInstance(0, 0, 20 * NANOS_PER_MINUTE), Duration.from("P0000-00-00T00:20:00"));
        assertEquals(Duration.newInstance(0, 0, 56 * NANOS_PER_SECOND), Duration.from("P0000-00-00T00:00:56"));
        assertEquals(Duration.newInstance(15, 0, 130 * NANOS_PER_MINUTE), Duration.from("P0001-03-00T02:10:00"));
    }
