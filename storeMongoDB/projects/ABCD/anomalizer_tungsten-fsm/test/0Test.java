    public void testMaxTransitionCount() throws Exception
    {
        // Construct and build the map. There is only one possible
        // transition from start to end2.
        State start = new State("START", StateType.START);
        State middle1 = new State("M1", StateType.ACTIVE);
        State middle2 = new State("M2", StateType.ACTIVE);
        State end = new State("END1", StateType.END);
        Transition s_m1 = new Transition("s_m1", new PositiveGuard(), start,
                null, middle1);
        Transition m1_m2 = new Transition("m1_m2", new PositiveGuard(),
                middle1, null, middle2);
        Transition m2_m1 = new Transition("m2_m1", new PositiveGuard(),
                middle2, null, middle1);
        Transition m2_e = new Transition("m2_e", new PositiveGuard(), middle2,
                null, end);

        StateTransitionMap map1 = new StateTransitionMap();
        map1.addState(start);
        map1.addState(end);
        map1.addState(middle1);
        map1.addState(middle2);
        map1.addTransition(s_m1);
        map1.addTransition(m1_m2);
        map1.addTransition(m2_m1);
        map1.addTransition(m2_e);
        map1.build();

        // Create a new state machine that permits three transitions.
        StateMachine sm1 = new StateMachine(map1, new EntityAdapter(null));
        sm1.setMaxTransitions(3);

        // Confirm that we can apply a messsage three times and get a
        // FiniteStateException on the fourth try.
        sm1.applyEvent(new Event(null));
        assertEquals("Expect midle1 state", middle1, sm1.getState());

        sm1.applyEvent(new Event(null));
        assertEquals("Expect middle2 state", middle2, sm1.getState());

        sm1.applyEvent(new Event(null));
        assertEquals("Expect middle1 state", middle1, sm1.getState());

        try
        {
            sm1.applyEvent(new Event(null));
            throw new Exception("Able to exceed maxTransitions count!");
        }
        catch (FiniteStateException e)
        {
            // OK
        }
    }
