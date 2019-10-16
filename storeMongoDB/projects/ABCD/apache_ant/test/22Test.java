    public void testAttributeSetters() throws BuildException {
        try {
            ih.setAttribute(p, this, "one", "test");
            fail("setOne doesn't exist");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.setAttribute(p, this, "two", "test");
            fail("setTwo returns non void");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.setAttribute(p, this, "three", "test");
            fail("setThree takes no args");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.setAttribute(p, this, "four", "test");
            fail("setFour takes two args");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.setAttribute(p, this, "five", "test");
            fail("setFive takes array arg");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        try {
            ih.setAttribute(p, this, "six", "test");
            fail("Project doesn't have a String constructor");
        } catch (BuildException be) {
            //TODO we should be asserting a value in here
        }
        ih.setAttribute(p, this, "seven", "2");
        try {
            ih.setAttribute(p, this, "seven", "3");
            fail("2 shouldn't be equals to three");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof ComparisonFailure);
        }
        ih.setAttribute(p, this, "eight", "2");
        try {
            ih.setAttribute(p, this, "eight", "3");
            fail("2 shouldn't be equals to three - as int");
        } catch (BuildException be) {
            assertTrue("Cause of error: " + be.toString(), be.getCause() instanceof AssertionError);
        }
        ih.setAttribute(p, this, "nine", "2");
        try {
            ih.setAttribute(p, this, "nine", "3");
            fail("2 shouldn't be equals to three - as Integer");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof AssertionError);
        }
        ih.setAttribute(p, this, "ten", "2");
        try {
            ih.setAttribute(p, this, "ten", "3");
            fail(projectBasedir+"2 shouldn't be equals to "+projectBasedir+"3");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof AssertionError);
        }
        ih.setAttribute(p, this, "eleven", "2");
        try {
            ih.setAttribute(p, this, "eleven", "on");
            fail("on shouldn't be false");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof AssertionError);
        }
        ih.setAttribute(p, this, "twelve", "2");
        try {
            ih.setAttribute(p, this, "twelve", "on");
            fail("on shouldn't be false");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof AssertionError);
        }
        ih.setAttribute(p, this, "thirteen", "org.apache.tools.ant.Project");
        try {
            ih.setAttribute(p, this, "thirteen", "org.apache.tools.ant.ProjectHelper");
            fail("org.apache.tools.ant.Project shouldn't be equal to org.apache.tools.ant.ProjectHelper");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof AssertionError);
        }
        try {
            ih.setAttribute(p, this, "thirteen", "org.apache.tools.ant.Project2");
            fail("org.apache.tools.ant.Project2 doesn't exist");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof ClassNotFoundException);
        }
        ih.setAttribute(p, this, "fourteen", "2");
        try {
            ih.setAttribute(p, this, "fourteen", "on");
            fail("2 shouldn't be equals to three - as StringBuffer");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof ComparisonFailure);
        }
        ih.setAttribute(p, this, "fifteen", "abcd");
        try {
            ih.setAttribute(p, this, "fifteen", "on");
            fail("o shouldn't be equal to a");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof AssertionError);
        }
        ih.setAttribute(p, this, "sixteen", "abcd");
        try {
            ih.setAttribute(p, this, "sixteen", "on");
            fail("o shouldn't be equal to a");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof AssertionError);
        }
        ih.setAttribute(p, this, "seventeen", "17");
        try {
            ih.setAttribute(p, this, "seventeen", "3");
            fail("17 shouldn't be equals to three");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof AssertionError);
        }
        ih.setAttribute(p, this, "eightteen", "18");
        try {
            ih.setAttribute(p, this, "eightteen", "3");
            fail("18 shouldn't be equals to three");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof AssertionError);
        }
        ih.setAttribute(p, this, "nineteen", "19");
        try {
            ih.setAttribute(p, this, "nineteen", "3");
            fail("19 shouldn't be equals to three");
        } catch (BuildException be) {
            assertTrue(be.getCause() instanceof AssertionError);
        }
    }
