	public void testDefaultConstructor() throws MimeTypeParseException {
        assertEquals("application/*", mimeType.getBaseType());
		assertEquals("application", mimeType.getPrimaryType());
        // not sure as RFC2045 does not allow "*" but this is what the RI does
		assertEquals("*", mimeType.getSubType());

		assertTrue(mimeType.match(new MimeType()));
		assertTrue(mimeType.match(new MimeType("application/*")));

        assertNull(mimeType.getParameter("foo"));
        assertEquals(0, mimeType.getParameters().size());
        assertTrue(mimeType.getParameters().isEmpty());
	}
