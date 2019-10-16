    public void test_longReadlink() throws Exception {
        File base = createTemporaryDirectory();
        File target = createDeepStructure(base);
        File source = new File(base, "source");
        assertFalse(source.exists());
        assertTrue(target.exists());
        assertTrue(target.getCanonicalPath().length() <= 159);
        ln_s(target, source);
        assertTrue(source.exists());
        assertEquals(target.getCanonicalPath(), source.getCanonicalPath());
    }
