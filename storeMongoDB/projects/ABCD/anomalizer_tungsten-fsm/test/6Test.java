    public void testLatches() throws Exception
    {
        // Create a state machine with multiple states and an error state.
        StateTransitionMap map = new StateTransitionMap();
        SampleAction sampleAction = new SampleAction();
        SampleAction errorAction = new SampleAction();
        State start = map.addState("START", StateType.START, null);
        State test = map.addState("TEST", StateType.ACTIVE, null);
        State error = map.addState("ERROR", StateType.ACTIVE, null,
                errorAction, null);
        State end = map.addState("END", StateType.END, null);

        map.setErrorState(error);

        map.addTransition("START-TO-TEST", "START-TO-TEST", start,
                sampleAction, test);
        map.addTransition("ERROR-TO-TEST", "ERROR-TO-TEST", error,
                sampleAction, test);
        map
                .addTransition("TEST-TO-END", "TEST-TO-END", test,
                        sampleAction, end);

        map.build();

        // Start the state machine.
        StateMachine sm = new StateMachine(map, new EntityAdapter(null));
        ExecutorService exec = Executors.newSingleThreadExecutor();

        // Verify that we can wait for a transition into a desired state.
        StateTransitionLatch latch1 = sm
                .createStateTransitionLatch(test, false);
        Future<State> result1 = exec.submit(latch1);
        sm.applyEvent(new StringEvent("START-TO-TEST"));
        State s1 = result1.get();
        assertEquals("Checking normal state transition", test, s1);
        assertTrue("Latch should be done", latch1.isDone());
        assertTrue("Latch should have found desired state", latch1.isExpected());
        assertFalse("Latch should not have found error", latch1.isError());

        // Verify that we can wait for a transition into the error state by
        // forcing a failure. (Wait for end, but really look for error.)
        StateTransitionLatch latch2 = sm.createStateTransitionLatch(end, true);
        Future<State> result2 = exec.submit(latch2);
        sampleAction.setFailure();
        try
        {
            sm.applyEvent(new StringEvent("TEST-TO-END"));
            throw new Exception(
                    "Failed transition did not throw TransitionFailedException");
        }
        catch (TransitionFailureException e)
        {
        }

        State s2 = result2.get();
        assertEquals("Should be in error state", error, s2);
        assertTrue("Latch should be done", latch2.isDone());
        assertFalse("Latch should not have found desired state", latch2
                .isExpected());
        assertTrue("Latch should have found error", latch2.isError());

        // Verify that we time out if we don't reach our expected state.
        StateTransitionLatch latch3 = sm.createStateTransitionLatch(end, false);
        Future<State> result3 = exec.submit(latch3);
        sampleAction.setSucceed();
        sm.applyEvent(new StringEvent("ERROR-TO-TEST"));

        try
        {
            result3.get(3000, TimeUnit.MILLISECONDS);
            throw new Exception("Operation did not time out!");
        }
        catch (TimeoutException e)
        {
        }
        assertFalse("Latch should not be done", latch3.isDone());
        assertFalse("Latch should not have found desired state", latch3
                .isExpected());
        assertFalse("Latch should not have found error", latch3.isError());
        
        // Verify that we can cancel the latch after letting it wait for a while.
        StateTransitionLatch latch4 = sm.createStateTransitionLatch(start, false);
        Future<State> result4 = exec.submit(latch3);
        Thread.sleep(500);  // Bide a wee
        result4.cancel(true); 
        assertFalse("Latch should not be done", latch4.isDone());
        assertFalse("Latch should not have found desired state", latch4
                .isExpected());
        assertFalse("Latch should not have found error", latch4.isError());
    }
