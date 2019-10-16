    public void lookupThroughModeTest() {
        String test = "Alfalfa/Lucerne";

        assertEquals("Invalid lookup results", test, LookupCodes.lookupCode("CRID", "AL", "cn", "DSSAT"));
    }
