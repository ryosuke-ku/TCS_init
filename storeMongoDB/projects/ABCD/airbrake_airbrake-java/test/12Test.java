	public void testExceptionToRubyBacktrace$UsingNewRubyBacktraceEmptyInstanceAsFactoryOfRubyBacktrace() {
		final Throwable EXCEPTION = newException("java.lang.RuntimeException: undefined method `password' for nil:NilClass");

		final Iterable<String> backtrace = new RubyBacktrace().newBacktrace(EXCEPTION);

		assertThat(backtrace, hasItem("at airbrake.Exceptions.java:16:in `newException'"));
	}
