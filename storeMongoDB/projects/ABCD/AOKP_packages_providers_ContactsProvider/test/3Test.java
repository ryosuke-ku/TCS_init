    public void testConcatenateClauses() {
        assertEquals("(first)", concatenateClauses("first"));
        assertEquals("(first) AND (second)", concatenateClauses("first", "second"));
        assertEquals("(second)", concatenateClauses("second", null));
        assertEquals("(second)", concatenateClauses(null, "second"));
        assertEquals("(second)", concatenateClauses(null, "second", null));
        assertEquals("(a) AND (b) AND (c)", concatenateClauses(null, "a", "b", null, "c"));
        assertEquals("(WHERE \"a\" = \"b\")", concatenateClauses(null, "WHERE \"a\" = \"b\""));
    }
