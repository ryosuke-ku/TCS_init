	public void testCompositePath() throws Exception {
        URISupport.CompositeData data = URISupport.parseComposite(new URI("test:(path)/path"));
        assertEquals("path", data.getPath());        
        data = URISupport.parseComposite(new URI("test:path"));
        assertNull(data.getPath());
    }
