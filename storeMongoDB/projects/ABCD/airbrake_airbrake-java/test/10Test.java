	public void testIgnoreNoiseBacktrace() {
		final Iterable<String> backtrace = new Backtrace(backtrace()) {
			@Override
			protected void ignore() {
				ignoreNoise();
			}
		};

		assertThat(backtrace, not(hasItem("inv1.invoke(:-1)")));
		assertThat(backtrace, not(hasItem("javax.servlet.http.HttpServlet.service(HttpServlet.java:820)")));
		assertThat(backtrace, not(hasItem("sun.reflect.GeneratedMethodAccessor338.invoke(null:-1)")));
		assertThat(backtrace, not(hasItem("sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)")));
		assertThat(backtrace, not(hasItem("java.lang.reflect.Method.invoke(Method.java:597)")));
	}
