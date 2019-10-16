	public void testParsingURI() throws Exception {
        URI source = new URI("tcp://localhost:61626/foo/bar?cheese=Edam&x=123");
        
        Map map = URISupport.parseParamters(source);
    
        assertEquals(("Size: " + map), 2, map.size());
        assertMapKey(map, "cheese", "Edam");
        assertMapKey(map, "x", "123");
        
        URI result = URISupport.removeQuery(source);
        
        assertEquals("result", new URI("tcp://localhost:61626/foo/bar"), result);
    }
