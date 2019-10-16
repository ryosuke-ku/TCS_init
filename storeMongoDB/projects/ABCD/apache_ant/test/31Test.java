    public void testElementCreators() throws BuildException {
        try {
            ih.getElementType("one");
            fail("don't have element type one");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.getElementType("two");
            fail("createTwo takes arguments");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.getElementType("three");
            fail("createThree returns void");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.getElementType("four");
            fail("createFour returns array");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.getElementType("five");
            fail("createFive returns primitive type");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        assertEquals(String.class, ih.getElementType("six"));
        assertEquals("test", ih.createElement(p, this, "six"));

        try {
            ih.getElementType("seven");
            fail("addSeven takes two arguments");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.getElementType("eight");
            fail("addEight takes no arguments");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.getElementType("nine");
            fail("nine return non void");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.getElementType("ten");
            fail("addTen takes array argument");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.getElementType("eleven");
            fail("addEleven takes primitive argument");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.getElementType("twelve");
            fail("no primitive constructor for java.lang.Class");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        assertEquals(StringBuffer.class, ih.getElementType("thirteen"));
        assertEquals("test", ih.createElement(p, this, "thirteen").toString());

        try {
            ih.createElement(p, this, "fourteen");
            fail("fourteen throws NullPointerException");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof NullPointerException);
        }

        try {
            ih.createElement(p, this, "fourteen");
            fail("fifteen throws NullPointerException");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof NullPointerException);
        }
    }
