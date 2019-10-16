	public void testOneArgsMethod() {
	    PojoMetadata manip = SimpleMultipleCheckServiceProvider;
        MethodMetadata method = manip.getMethods("refBind")[0];
		assertEquals("Check args count", method.getMethodArguments().length, 1);
        assertEquals("Check args", method.getMethodArguments()[0], "org.osgi.framework.ServiceReference");
		assertEquals("Check return", method.getMethodReturn(), "void");

        method = manip.getMethod("refBind", new String[] {"org.osgi.framework.ServiceReference"});
        assertEquals("Check args count", method.getMethodArguments().length, 1);
        assertEquals("Check args", method.getMethodArguments()[0], "org.osgi.framework.ServiceReference");
        assertEquals("Check return", method.getMethodReturn(), "void");
	}
