	public void testIgnoreMozillaBacktrace() {
		final Iterable<String> backtrace = new Backtrace(backtrace()) {
			@Override
			protected void ignore() {
				ignoreMozilla();
			}
		};

		assertThat(backtrace, not(hasItem("org.mozilla.javascript.FunctionObject.doInvoke(FunctionObject.java:523)")));
		assertThat(backtrace, not(hasItem("org.mozilla.javascript.ScriptRuntime.call(ScriptRuntime.java:1,244)")));
		assertThat(backtrace, not(hasItem("org.mozilla.javascript.continuations.ContinuationInterpreter.interpret(ContinuationInterpreter.java:1,134)")));
		assertThat(backtrace, not(hasItem("org.mozilla.javascript.ScriptRuntime.call(ScriptRuntime.java:1,244)")));
		assertThat(backtrace, not(hasItem("org.mozilla.javascript.ScriptableObject.callMethod(ScriptableObject.java:1,591)")));
		assertThat(backtrace, not(hasItem("org.mozilla.javascript.FunctionObject.doInvoke(FunctionObject.java:523)")));
	}
