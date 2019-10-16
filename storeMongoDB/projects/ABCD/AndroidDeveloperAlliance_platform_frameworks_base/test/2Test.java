    public void testRowNameContentUri() {
        ContentResolver r = getContext().getContentResolver();

        assertEquals("content://settings/system/test_setting",
                Settings.System.getUriFor("test_setting").toString());
        assertEquals("content://settings/secure/test_service",
                Settings.Secure.getUriFor("test_service").toString());

        // These tables use the row name (not ID) as their content URI.
        Uri tables[] = { Settings.System.CONTENT_URI, Settings.Secure.CONTENT_URI };
        for (Uri table : tables) {
            ContentValues v = new ContentValues();
            v.put(Settings.System.NAME, "test_key");
            v.put(Settings.System.VALUE, "Test");
            Uri uri = r.insert(table, v);
            assertEquals(table.toString() + "/test_key", uri.toString());

            // Query with a specific URI and no WHERE clause succeeds.
            Cursor c = r.query(uri, null, null, null, null);
            try {
                assertTrue(c.moveToNext());
                assertEquals("test_key", c.getString(c.getColumnIndex(Settings.System.NAME)));
                assertEquals("Test", c.getString(c.getColumnIndex(Settings.System.VALUE)));
                assertFalse(c.moveToNext());
            } finally {
                c.close();
            }

            // Query with a specific URI and a WHERE clause fails.
            try {
                r.query(uri, null, "1", null, null);
                fail("UnsupportedOperationException expected");
            } catch (UnsupportedOperationException e) {
                if (!e.toString().contains("WHERE clause")) throw e;
            }

            // Query with a tablewide URI and a WHERE clause succeeds.
            c = r.query(table, null, "name='test_key'", null, null);
            try {
                assertTrue(c.moveToNext());
                assertEquals("test_key", c.getString(c.getColumnIndex(Settings.System.NAME)));
                assertEquals("Test", c.getString(c.getColumnIndex(Settings.System.VALUE)));
                assertFalse(c.moveToNext());
            } finally {
                c.close();
            }

            v = new ContentValues();
            v.put(Settings.System.VALUE, "Toast");
            assertEquals(1, r.update(uri, v, null, null));

            c = r.query(uri, null, null, null, null);
            try {
                assertTrue(c.moveToNext());
                assertEquals("test_key", c.getString(c.getColumnIndex(Settings.System.NAME)));
                assertEquals("Toast", c.getString(c.getColumnIndex(Settings.System.VALUE)));
                assertFalse(c.moveToNext());
            } finally {
                c.close();
            }

            assertEquals(1, r.delete(uri, null, null));
        }

        assertEquals(null, Settings.System.getString(r, "test_key"));
        assertEquals(null, Settings.Secure.getString(r, "test_key"));
    }
