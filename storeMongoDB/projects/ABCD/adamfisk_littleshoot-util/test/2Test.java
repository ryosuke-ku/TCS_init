    @Test public void testStripHost() throws Exception 
        {
        final String uri = "http://test.com/pathIsHere";
        final String noHostUri = UriUtils.stripHost(uri);
        assertEquals("/pathIsHere", noHostUri);
        }
