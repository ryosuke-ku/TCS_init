    public void testMaybeQuote()
    {
        String unquotable = "a";
        assertEquals(unquotable, ColumnIdentifier.maybeQuote(unquotable));
        unquotable = "z4";
        assertEquals(unquotable, ColumnIdentifier.maybeQuote(unquotable));
        unquotable = "m_4_";
        assertEquals(unquotable, ColumnIdentifier.maybeQuote(unquotable));
        unquotable = "f__";
        assertEquals(unquotable, ColumnIdentifier.maybeQuote(unquotable));
        
        assertEquals("\"A\"", ColumnIdentifier.maybeQuote("A"));
        assertEquals("\"4b\"", ColumnIdentifier.maybeQuote("4b"));
        assertEquals("\"\"\"\"", ColumnIdentifier.maybeQuote("\""));
        assertEquals("\"\"\"a\"\"b\"\"\"", ColumnIdentifier.maybeQuote("\"a\"b\""));
    }
