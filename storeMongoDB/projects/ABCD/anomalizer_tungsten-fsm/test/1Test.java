    public void testLoopImplementation() throws Exception
    {
        Integer entity = new Integer(0);

        // Create an action to increment the entity each time we enter a state.
        Action incrementorAction = new Action()
        {
            public void doAction(Event ev, Entity entity,
                    Transition transition, int actionType)
            {
                EntityAdapter ea = (EntityAdapter) entity;
                int current = ((Integer) ea.getEntity()).intValue();
                Integer next = new Integer(current + 1);
                ea.setEntity(next);
            }
        };

        // Create a guard that returns true if the entity count is equal to or
        // greater than 10.
        Guard numberGuard = new Guard()
        {
            public boolean accept(Event message, Entity entity, State state)
            {
                EntityAdapter ea = (EntityAdapter) entity;
                int current = ((Integer) ea.getEntity()).intValue();
                return (current >= 10);
            }
        };

        // Construct and build the map. There is only one possible
        // transition from start to end2.
        State start = new State("START", StateType.START);
        State end = new State("END1", StateType.END);
        Transition s_2_s = new Transition("START-START", new NegationGuard(
                numberGuard), start, incrementorAction, start);
        Transition s_2_e = new Transition("START-END", numberGuard, start,
                null, end);

        StateTransitionMap map1 = new StateTransitionMap();
        map1.addState(start);
        map1.addState(end);
        map1.addTransition(s_2_s);
        map1.addTransition(s_2_e);
        map1.build();

        // Create a new state machine that permits three transitions.
        StateMachine sm1 = new StateMachine(map1, new EntityAdapter(entity));
        SampleListener listener = new SampleListener();
        sm1.setMaxTransitions(11);
        sm1.addListener(listener);

        // Continue to apply events until we reach the end state.
        int counter = 0;
        while (!sm1.getState().isEnd())
        {
            sm1.applyEvent(new Event(null));
            counter++;
        }

        // Assert that we looped through 11 times.
        assertEquals("Events applied 11 times", 11, counter);

        // Assert the entity counter is 10 as well.
        Integer finalInteger = (Integer) ((EntityAdapter) sm1.getEntity())
                .getEntity();
        int finalInt = finalInteger.intValue();
        assertEquals("Entity counter is 10", 10, finalInt);

        // Assert that there was one state transition recorded by the
        // listener.
        assertEquals("State changes", 1, listener.getChanges());
    }
