    public void testGetSizeFileResource() {
        ResourceImpl res = new ResourceImpl();
        res.put(Property.URI, "repo_files/test_file_3.jar");

        final URL dir = getClass().getResource("/repo_files");
        Repository repo = new RepositoryImpl() {
            { setURI(dir.toExternalForm()); }
        };
        res.setRepository(repo);

        assertEquals("Should have obtained the file size", 3, (long) res.getSize());
    }
