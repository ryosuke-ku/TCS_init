    public void testNameValueCache() {
        ContentResolver r = getContext().getContentResolver();
        Settings.Secure.putString(r, "test_service", "Value");
        assertEquals("Value", Settings.Secure.getString(r, "test_service"));

        // Make sure the value can be overwritten.
        Settings.Secure.putString(r, "test_service", "New");
        assertEquals("New", Settings.Secure.getString(r, "test_service"));

        // Also that delete works.
        assertEquals(1, r.delete(Settings.Secure.getUriFor("test_service"), null, null));
        assertEquals(null, Settings.Secure.getString(r, "test_service"));

        // Try all the same things in the System table
        Settings.System.putString(r, "test_setting", "Value");
        assertEquals("Value", Settings.System.getString(r, "test_setting"));

        Settings.System.putString(r, "test_setting", "New");
        assertEquals("New", Settings.System.getString(r, "test_setting"));

        assertEquals(1, r.delete(Settings.System.getUriFor("test_setting"), null, null));
        assertEquals(null, Settings.System.getString(r, "test_setting"));
    }
