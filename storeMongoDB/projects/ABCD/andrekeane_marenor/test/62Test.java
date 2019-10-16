    public void testRestore_InvalidStateDocument()
    {
        try
        {
            String badStateInXml = "!!invalid xml string!!";
            AnnotationAttributes attrib = new AnnotationAttributes();
            attrib.restoreState(badStateInXml);

            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
        }
    }
