	public void testValidaBacktrace() {
		assertTrue(isValidBacktrace("at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)"));
		assertTrue(isValidBacktrace("vendors/rails/actionpack/lib/action_controller/filter.rb:579:in `call_filters'"));
	}
