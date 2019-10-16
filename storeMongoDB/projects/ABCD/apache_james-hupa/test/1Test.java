    public void testRegexBadAttributes() {

        String txt, res;
        txt = "... <div attr=a onClick=\"something('');\"> ...";
        res = RegexPatterns.replaceAllRecursive(txt, RegexPatterns.regex_badAttrs, RegexPatterns.repl_badAttrs);
        assertEquals("... <div attr=a> ...", res);

        txt = "... <div attr=a onClick=\"something('');\" attr=b onMouseOver=whatever attr=c onKeyup=\"\" /> ...";
        res = RegexPatterns.replaceAllRecursive(txt, RegexPatterns.regex_badAttrs, RegexPatterns.repl_badAttrs);
        assertEquals("... <div attr=a attr=b attr=c /> ...", res);
    }
