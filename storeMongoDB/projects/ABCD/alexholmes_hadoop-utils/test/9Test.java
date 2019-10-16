    public void testFieldSeparator() throws IOException {
        assertEquals(" ", config.getFieldSeparator(" "));
        assertEquals("-", config.setFieldSeparator("-").getFieldSeparator(" "));
    }
