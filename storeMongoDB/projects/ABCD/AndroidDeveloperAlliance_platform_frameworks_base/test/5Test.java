    public void testListPreferenceSummaryFromEntries() {
        String[] entries = { "one", "two", "three" };
        String[] entryValues = { "1" , "2", "3" };
        ListPreference lp = new ListPreference(getContext());
        lp.setEntries(entries);
        lp.setEntryValues(entryValues);

        lp.setValue(entryValues[1]);
        assertTrue(lp.getSummary() == null);

        lp.setSummary("%1$s");
        assertEquals(entries[1], lp.getSummary());

        lp.setValue(entryValues[2]);
        assertEquals(entries[2], lp.getSummary());

        lp.setSummary(null);
        assertTrue(lp.getSummary() == null);

        lp.setSummary("The color is %1$s");
        assertEquals("The color is " + entries[2], lp.getSummary());
    }
