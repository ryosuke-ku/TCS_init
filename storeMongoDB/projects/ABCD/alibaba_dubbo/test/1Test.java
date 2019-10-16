    public void testInterpolateSequence() throws Exception {
        String regexp = ParseUtils.interpolate("1.0.[0-9]", new HashMap<String, String>());
        assertEquals("1.0.[0-9]", regexp.toString());
    }
