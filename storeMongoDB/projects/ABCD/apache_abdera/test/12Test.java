    public void testCondense() {
        String[] types = MimeTypeHelper.condense("image/png", "image/gif", "image/png", "image/*");
        assertEquals(1, types.length);
        assertEquals("image/*", types[0]);
    }
