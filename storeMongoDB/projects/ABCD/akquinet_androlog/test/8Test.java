    public void testDefaultLogLevel() {
        String tag = "testLevel";
        Log.setDefaultLogLevel(Constants.DEBUG);
        assertTrue(Log.isLoggable(tag, Constants.DEBUG));

        Log.setDefaultLogLevel(Constants.INFO);
        assertFalse(Log.isLoggable(tag, Constants.DEBUG));

        // Test with this
        Log.setDefaultLogLevel(Constants.DEBUG);
        assertTrue(Log.isLoggable(this, Constants.DEBUG));

        Log.setDefaultLogLevel(Constants.INFO);
        assertFalse(Log.isLoggable(this, Constants.DEBUG));
    }
