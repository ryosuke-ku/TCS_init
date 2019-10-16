    public void testStartKey() throws IOException {
        assertNull(config.getStartKey());
        assertEquals(new Integer(4), config.setStartKey(4).getStartKey());
    }
