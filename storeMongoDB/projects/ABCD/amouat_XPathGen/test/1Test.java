    public final void testNewlineIsNotEmpty() {
        Document testDoc = createDocument("<a>text</a>");
        
        Node text1 = testDoc.createTextNode("\r");
        Node text2 = testDoc.createTextNode("\r\n");
        Node text3 = testDoc.createTextNode("\n");
        
        assertFalse(XPathGen.nodeIsEmptyText(text1));
        assertEquals(1, text1.getNodeValue().length());
        assertFalse(XPathGen.nodeIsEmptyText(text2));
        assertEquals(2, text2.getNodeValue().length());
        assertFalse(XPathGen.nodeIsEmptyText(text3));
        assertEquals(1, text3.getNodeValue().length());
    }
