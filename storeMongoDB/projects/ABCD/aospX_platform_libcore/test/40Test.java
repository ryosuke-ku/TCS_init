    public void test_getSpace() throws Exception {
        assertTrue(new File("/").getFreeSpace() >= 0);
        assertTrue(new File("/").getTotalSpace() >= 0);
        assertTrue(new File("/").getUsableSpace() >= 0);
    }
