	public void testInterface() {
	    PojoMetadata manip = FooProviderType1;

        String[] itf = manip.getInterfaces();
		assertEquals("Check interfaces number", itf.length, 1);
		assertEquals("Check itf name", itf[0], "org.apache.felix.ipojo.test.scenarios.manipulation.service.FooService");

        assertTrue("Check Foo Service implementation", manip.isInterfaceImplemented("org.apache.felix.ipojo.test.scenarios.manipulation.service.FooService"));
        assertFalse("Check Bar Service implementation", manip.isInterfaceImplemented("org.apache.felix.ipojo.test.scenarios.manipulation.service.BarService"));
	}
