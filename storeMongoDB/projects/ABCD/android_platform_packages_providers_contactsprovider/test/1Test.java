    public void testEmptyClauses() {
        assertEquals(null, new SelectionBuilder(null).build());
        assertEquals(null, new SelectionBuilder("").build());
        assertEquals(null, new SelectionBuilder("").addClause(null).build());
        assertEquals(null, new SelectionBuilder(null).addClause("").build());
        assertEquals(null, new SelectionBuilder(null).addClause("").addClause(null).build());
    }
