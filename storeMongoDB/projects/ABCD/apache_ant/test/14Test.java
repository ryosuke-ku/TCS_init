    public void testDataTypes() throws BuildException {
        assertNull("dummy is not a known data type",
                   p.createDataType("dummy"));
        Object o = p.createDataType("fileset");
        assertNotNull("fileset is a known type", o);
        assertTrue("fileset creates FileSet", o instanceof FileSet);
        assertTrue("PatternSet",
               p.createDataType("patternset") instanceof PatternSet);
        assertTrue("Path", p.createDataType("path") instanceof Path);
    }
