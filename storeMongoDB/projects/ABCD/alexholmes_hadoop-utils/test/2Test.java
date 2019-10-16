    public void testUnique() throws IOException {
        assertFalse(config.getUnique());
        assertTrue(config.setUnique(true).getUnique());
        assertFalse(config.setUnique(false).getUnique());
    }
