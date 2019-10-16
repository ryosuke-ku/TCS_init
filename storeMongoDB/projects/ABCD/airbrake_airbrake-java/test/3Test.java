	public void testIgnoreExceptionGenerateInsideTest() {
		final Throwable EXCEPTION = newException(ERROR_MESSAGE);

		final Iterable<String> backtrace = new Backtrace(strings(ExceptionUtils.getStackTrace(EXCEPTION))) {
			@Override
			protected void ignore() {
				ignoreJunit();
				ignoreEclipse();
				ignoreNoise();
			}

		};

		assertThat(backtrace, not(hasItem("	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)")));
		assertThat(backtrace, not(hasItem("	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)")));
		assertThat(backtrace, not(hasItem("	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)")));
		assertThat(backtrace, not(hasItem("	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)")));
		assertThat(backtrace, not(hasItem("	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)")));
		assertThat(backtrace, not(hasItem("	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)")));
		assertThat(backtrace, not(hasItem("	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)")));
		assertThat(backtrace, not(hasItem("	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)")));

		assertThat(backtrace, not(hasItem("	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:45)")));
		assertThat(backtrace, not(hasItem("	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)")));
		assertThat(backtrace, not(hasItem("	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:460)")));

		assertThat(backtrace, not(hasItem("	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)")));
		assertThat(backtrace, not(hasItem("	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)")));
		assertThat(backtrace, not(hasItem("	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)")));
	}
