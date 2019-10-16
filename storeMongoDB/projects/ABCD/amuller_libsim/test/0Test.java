    public void testGetSimilarity() {

        float result = metric.getSimilarity("Test String1", "Test String2");

        assertEquals(0.33333334f, result);
    }
