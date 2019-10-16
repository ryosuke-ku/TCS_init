	public void testComposite() throws Exception {
        URISupport.CompositeData data = URISupport.parseComposite(new URI("test:(part1://host,part2://(sub1://part,sube2:part))"));
        assertEquals(2, data.getComponents().length);
    }
