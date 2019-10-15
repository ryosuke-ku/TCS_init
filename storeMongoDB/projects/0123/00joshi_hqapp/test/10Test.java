    public void testAbsolute5() {
        //testing drive letter search from root:
        assumeTrue("Can't use drive letters on non DOS or Netware systems",
                Os.isFamily("dos") || Os.isFamily("netware"));

        String pattern = new File(File.separator).getAbsolutePath().toUpperCase() + "*";
        ds.setIncludes(new String[] {pattern});
        ds.scan();
        //if this is our context we assume there must be something here:
        assertTrue("should have at least one resident file",
            ds.getIncludedFilesCount() + ds.getIncludedDirsCount() > 0);
    }
