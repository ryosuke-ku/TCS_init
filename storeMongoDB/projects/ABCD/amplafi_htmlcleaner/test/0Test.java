    public void testEscapeXml_recognizeUnicodeChars() {
        String res = Utils.escapeXml("[&alpha;][&eacute;][&oline;]", true, false, true, false, false, false);
        assertEquals("[&#945;][&#233;][&#8254;]", res);
        
        res = Utils.escapeXml("[&alpha;][&eacute;][&oline;][&#931;]", true, true, true, false, false, false);
