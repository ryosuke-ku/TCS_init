    public void testFile() {
        File f = new File("/tmp/bob");

        Uri uri = Uri.fromFile(f);

        assertEquals("file:///tmp/bob", uri.toString());
    }
