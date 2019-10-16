    public void testRewriteCorpDirectories() {
        // 6 columns
        final MatrixCursor c = new MatrixCursor(new String[] {
                Directory._ID,
                Directory.PACKAGE_NAME,
                Directory.TYPE_RESOURCE_ID,
                Directory.DISPLAY_NAME,
                Directory.ACCOUNT_TYPE,
                Directory.ACCOUNT_NAME,
        });

        // First, convert and make sure it returns an empty cursor.
        Cursor rewritten = ContactsProvider2.rewriteCorpDirectories(c);

        assertEquals(0, rewritten.getCount());
        assertEquals(6, rewritten.getColumnCount());

        c.addRow(new Object[] {
                5L, // Directory._ID
                "name", // Directory.PACKAGE_NAME
                123, // Directory.TYPE_RESOURCE_ID
                "display", // Directory.DISPLAY_NAME
                "atype", // Directory.ACCOUNT_TYPE
                "aname", // Directory.ACCOUNT_NAME
        });

        rewritten = ContactsProvider2.rewriteCorpDirectories(c);
        assertEquals(1, rewritten.getCount());
        assertEquals(6, rewritten.getColumnCount());

        rewritten.moveToPosition(0);
        int column = 0;
        assertEquals(1000000005L, rewritten.getLong(column++));
        assertEquals("name", rewritten.getString(column++));
        assertEquals(123, rewritten.getInt(column++));
        assertEquals("display", rewritten.getString(column++));
        assertEquals("atype", rewritten.getString(column++));
        assertEquals("aname", rewritten.getString(column++));
    }
