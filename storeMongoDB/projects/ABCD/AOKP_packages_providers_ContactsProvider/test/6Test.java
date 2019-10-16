    public void testEscapeLikeValuesEscapesUnderscores() {
        StringBuilder sb = new StringBuilder();
        DbQueryUtils.escapeLikeValue(sb, "my_test_string", '\\');
        assertEquals("my\\_test\\_string", sb.toString());

        sb = new StringBuilder();
        DbQueryUtils.escapeLikeValue(sb, "_test_", '\\');
        assertEquals("\\_test\\_", sb.toString());
    }
