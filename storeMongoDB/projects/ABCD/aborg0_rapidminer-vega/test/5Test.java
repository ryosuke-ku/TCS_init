	public void testProcess() throws OperatorException, RepositoryException {
		for (Operator op : process.getRootOperator().getAllInnerOperators()) {
			op.setBreakpoint(BreakpointListener.BREAKPOINT_AFTER, false);
			op.setBreakpoint(BreakpointListener.BREAKPOINT_BEFORE, false);
		}
		process.getRootOperator().setBreakpoint(BreakpointListener.BREAKPOINT_AFTER, false);
		process.getRootOperator().setBreakpoint(BreakpointListener.BREAKPOINT_BEFORE, false);

		IOContainer results = process.run();
		RapidAssert.assertEquals(Util.getExpectedResult(process), results.asList());
	}
