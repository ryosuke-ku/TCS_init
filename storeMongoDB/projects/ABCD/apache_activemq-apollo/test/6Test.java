	public void testCheckParenthesis() throws Exception {
        String str = "fred:(((ddd))";
        assertFalse(URISupport.checkParenthesis(str));
        str += ")";
        assertTrue(URISupport.checkParenthesis(str));
    }
