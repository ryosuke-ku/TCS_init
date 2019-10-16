    public void testEndKey() throws IOException {
        assertNull(config.getEndKey());
        assertEquals(new Integer(5), config.setEndKey(5).getEndKey());
    }
