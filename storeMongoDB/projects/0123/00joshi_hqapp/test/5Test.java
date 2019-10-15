    public void testAbsolute3() {
        buildRule.getProject().executeTarget("extended-setup");

        String tmpdir = buildRule.getProject().getProperty("output").replace(
                File.separatorChar, '/');
        ds.setIncludes(new String[] {tmpdir + "/**/*"});
        ds.setExcludes(new String[] {"**/alpha", "**/delta/*"});
        ds.scan();
        compareFiles(ds,
                new String[] {tmpdir + "/alpha/beta/beta.xml",
                        tmpdir + "/alpha/beta/gamma/gamma.xml"},
                new String[] {tmpdir + "/alpha/beta",
                        tmpdir + "/alpha/beta/gamma",
                        tmpdir + "/delta"});
    }
