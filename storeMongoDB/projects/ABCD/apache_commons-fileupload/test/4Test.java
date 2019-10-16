    public void testContentTypeParsing() {
        String s = "text/plain; Charset=UTF-8";
        ParameterParser parser = new ParameterParser();
        parser.setLowerCaseNames(true);
        Map<String, String> params = parser.parse(s, ';');
        assertEquals("UTF-8", params.get("charset"));
    }
