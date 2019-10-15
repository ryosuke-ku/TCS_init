    public void testRecursiveExcludes() {
        ds.setBasedir(new File(buildRule.getProject().getProperty("output")));
        ds.setExcludes(new String[] {"**/beta/**"});
        ds.scan();
        List<String> dirs = Arrays.asList(ds.getExcludedDirectories());
        assertEquals(2, dirs.size());
        assertThat("beta is excluded", dirs,
                hasItem("alpha/beta".replace('/', File.separatorChar)));
        assertThat("gamma is excluded", dirs,
                hasItem("alpha/beta/gamma".replace('/', File.separatorChar)));
        List<String> files = Arrays.asList(ds.getExcludedFiles());
        assertEquals(2, files.size());
        assertThat("beta.xml is excluded", files,
                hasItem("alpha/beta/beta.xml".replace('/', File.separatorChar)));
        assertThat("gamma.xml is excluded", files,
                hasItem("alpha/beta/gamma/gamma.xml".replace('/', File.separatorChar)));
    }
