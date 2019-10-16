    public void testAddSQL()
    {
        StringBuilder builder = new StringBuilder();
        expr.addSQL(builder, 0);
        assertEquals("", builder.toString());
        expr.addSQL(builder, DBExpr.CTX_DEFAULT);
        assertEquals("FIRSTNAME='JUnit'", builder.toString());
    }
