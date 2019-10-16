	public void testTwoArgsMethod() {
	    PojoMetadata manip = SimpleMultipleCheckServiceProvider;
        MethodMetadata method = manip.getMethods("doNothing")[0];
        assertEquals("Check args count", 2, method.getMethodArguments().length);
		assertEquals("Check args - 1", method.getMethodArguments()[0], "java.lang.Object");
        assertEquals("Check args - 2", method.getMethodArguments()[1], "java.lang.String");
		assertEquals("Check return", method.getMethodReturn(), "java.lang.Object");

        method = manip.getMethod("doNothing", new String[] {"java.lang.Object", "java.lang.String"});
        assertEquals("Check args count", 2, method.getMethodArguments().length);
        assertEquals("Check args - 1", method.getMethodArguments()[0], "java.lang.Object");
        assertEquals("Check args - 2", method.getMethodArguments()[1], "java.lang.String");
        assertEquals("Check return", method.getMethodReturn(), "java.lang.Object");
	}
