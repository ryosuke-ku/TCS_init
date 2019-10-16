    public void testGetQueryParameterNamesEmptyKey2() {
        Uri uri = Uri.parse("http://www.google.com/?a=x&=d&c=z");
        Set<String> names = uri.getQueryParameterNames();
        Iterator<String> iter = names.iterator();
        assertEquals(3, names.size());
        assertEquals("a", iter.next());
        assertEquals("", iter.next());
        assertEquals("c", iter.next());
    }
