    public void testContentsExcluded() {
        ds.setBasedir(new File("."));
        ds.setIncludes(new String[] {"**"});
        ds.addDefaultExcludes();
        ds.ensureNonPatternSetsReady();
        File f = new File(".svn");
        TokenizedPath p = new TokenizedPath(f.getAbsolutePath());
        assertTrue(ds.contentsExcluded(p));
    }
