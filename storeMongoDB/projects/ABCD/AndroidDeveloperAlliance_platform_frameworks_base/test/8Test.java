    public void testAddMeasurementString() throws Exception {
        MockPerformanceResultsWriter writer = new MockPerformanceResultsWriter();
        mPerfCollector.setPerformanceResultsWriter(writer);
        mPerfCollector.startTiming("testAddMeasurementString");
        mPerfCollector.addMeasurement("testAddMeasurementStringNull", null);
        mPerfCollector.addMeasurement("testAddMeasurementStringEmpty", "");
        mPerfCollector.addMeasurement("testAddMeasurementStringNonEmpty", "Hello World");
        mPerfCollector.stopTiming("");

        assertEquals("testAddMeasurementString", writer.timingLabel);
        Bundle results = writer.timingResults;
        assertEquals(4, results.size());
        assertTrue(results.containsKey("testAddMeasurementStringNull"));
        assertNull(results.getString("testAddMeasurementStringNull"));
        assertTrue(results.containsKey("testAddMeasurementStringEmpty"));
        assertEquals("", results.getString("testAddMeasurementStringEmpty"));
        assertTrue(results.containsKey("testAddMeasurementStringNonEmpty"));
        assertEquals("Hello World", results.getString("testAddMeasurementStringNonEmpty"));
    }
