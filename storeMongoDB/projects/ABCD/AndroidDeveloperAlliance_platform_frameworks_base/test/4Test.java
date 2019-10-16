    public void testRowNumberContentUri() {
        ContentResolver r = getContext().getContentResolver();

        // The bookmarks table (and everything else) uses standard row number content URIs.
        Uri uri = Settings.Bookmarks.add(r, new Intent("TEST"),
                "Test Title", "Test Folder", '*', 123);

        assertTrue(ContentUris.parseId(uri) > 0);

        assertEquals("TEST", Settings.Bookmarks.getIntentForShortcut(r, '*').getAction());

        ContentValues v = new ContentValues();
        v.put(Settings.Bookmarks.INTENT, "#Intent;action=TOAST;end");
        assertEquals(1, r.update(uri, v, null, null));

        assertEquals("TOAST", Settings.Bookmarks.getIntentForShortcut(r, '*').getAction());

        assertEquals(1, r.delete(uri, null, null));

        assertEquals(null, Settings.Bookmarks.getIntentForShortcut(r, '*'));
    }
