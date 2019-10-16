    public void testGetInEqualityClause() {
        assertEquals("(foo != 2)", DbQueryUtils.getInequalityClause("foo", 2));
    }
