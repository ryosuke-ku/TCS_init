    public void testRestore_CustomDefaults()
    {
        AnnotationAttributes defaults = new AnnotationAttributes();
        assignExampleValues(defaults);
        AnnotationAttributes attrib = new AnnotationAttributes();
        attrib.setDefaults(defaults);

        String stateInXml = attrib.getRestorableState();
        attrib = new AnnotationAttributes();
        attrib.restoreState(stateInXml);

        AnnotationAttributes expectedDefaults = new AnnotationAttributes();
        assignExampleValues(expectedDefaults);
        AnnotationAttributes expected = new AnnotationAttributes();
        expected.setDefaults(expectedDefaults);

        // "expected" and "attrib" will return values from their defaults.
        assertEquals(expected, attrib);
    }
