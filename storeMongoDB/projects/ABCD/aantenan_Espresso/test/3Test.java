    public void testLessThanFloat() throws SQLException {
        final SqlSelect statement = (SqlSelect) SqlParser.parse
                ("select * from TestDeals where child < 2.0;");
        final CompilerVisitor<TestDeal> visitor = new CompilerVisitor<TestDeal>(TestDeal.class, statement.getWhereClause());
        final Evaluator evaluator = visitor.compile();
        final TestDeal deal = new TestDeal();

        deal.setChild(1);
        assertTrue(evaluator.matches(deal));

        deal.setChild(2);
        assertFalse(evaluator.matches(deal));
    }
