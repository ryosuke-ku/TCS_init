    public void testIsAssertLoggable() {
        assertTrue(Log.isLoggable("any", Constants.ASSERT));
        Log.deactivateLogging();
        assertTrue(Log.isLoggable("any", Constants.ASSERT));
        assertTrue(Log.isLoggable(this, Constants.ASSERT));
        Log.activateLogging();
        assertTrue(Log.isLoggable("any", Constants.ASSERT));
        assertTrue(Log.isLoggable(this, Constants.ASSERT));
    }
