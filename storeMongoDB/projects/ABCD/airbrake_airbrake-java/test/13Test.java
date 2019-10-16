	public void testIgnoreIgnomreCommonsBacktrace() {
		final Iterable<String> backtrace = new QuietRubyBacktrace(backtrace());
		final Iterable<String> filteredBacktrace = new QuietRubyBacktrace(filteredBacktrace());

		assertEquals(filteredBacktrace.toString(), backtrace.toString());
	}
