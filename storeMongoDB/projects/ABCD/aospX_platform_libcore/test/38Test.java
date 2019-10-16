    private static File createTemporaryDirectory() throws Exception {
        String base = System.getProperty("java.io.tmpdir");
        File directory = new File(base, UUID.randomUUID().toString());
        assertTrue(directory.mkdirs());
        return directory;
    }
