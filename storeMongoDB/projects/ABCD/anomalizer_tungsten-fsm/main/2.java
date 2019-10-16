    public synchronized void applyEvent(Event<?> event)
            throws FiniteStateException
    {
        TransitionFailureException deferredException = null;

        if (maxTransitions > 0)
        {
            transitions++;
            if (transitions > maxTransitions)
                throw new FiniteStateException(
                        "Max transition count exceeded: state="
                                + state.getName() + " transition count="
                                + transitions);
        }

        // Find the next transition. This is guaranteed to be non-null.
        Transition<ET, ?> transition = map.nextTransition(state, event, entity);
        State<ET> nextState = transition.getOutput();
        logger.debug("Executing state transition: input state={} transition={} output state={}",
                new Object[] {state.getName(), transition.getName(), nextState.getName()});

        int actionType = -1;
        try
        {
            // Compute the least common parent between the current and next
            // state. Entry and exit actions fire below this state only in
            // the state hierarchy.
            State<ET> leastCommonParent = state.getLeastCommonParent(nextState);

            // If we are transitioning to a new state look for exit actions.
            if (state != nextState)
            {
                // Fire exit actions up to the state below the least common
                // parent
                // if it exists.
                State<ET> exitState = state;
                logger.debug("Searching for exit actions for current state: {}",
                                state.getName());

                while (exitState != null && exitState != leastCommonParent)
                {
                    if (exitState.getExitAction() != null)
                    {
                        Action<ET> exitAction = exitState.getExitAction();
                        actionType = Action.EXIT_ACTION;
                        logger.debug("Executing exit action for state: {}",
                                exitState.getName());
                        exitAction.doAction(event, entity, transition,
                                actionType);
                    }

                    exitState = exitState.getParent();
                }
            }

            // Fire transition action if it exists.
            if (transition.getAction() != null)
            {
                Action<ET> transitionAction = transition.getAction();
                actionType = Action.TRANSITION_ACTION;
                logger.debug("Executing action for transition: {}",
                        transition.getName());
                transitionAction
                        .doAction(event, entity, transition, actionType);
            }

            // If we are transitioning to a new state look for entry actions.
            if (state != nextState)
            {
                logger.debug("Searching for entry actions for next state: {}",
                        nextState.getName());

                // Fire entry actions from the state below the least common
                // parent (if there is one) to the next state itself.
                State<ET>[] entryStates = nextState.getHierarchy();
                int startIndex = -1;
                if (leastCommonParent == null)
                    startIndex = 0;
                else
                {
                    for (int i = 0; i < entryStates.length; i++)
                    {
                        if (entryStates[i] == leastCommonParent)
                        {
                            startIndex = i + 1;
                            break;
                        }
                    }
                }

                for (int i = startIndex; i < entryStates.length; i++)
                {
                    State<ET> entryState = entryStates[i];
                    if (entryState.getEntryAction() != null)
                    {
                        Action<ET> entryAction = entryState.getEntryAction();
                        actionType = Action.ENTER_ACTION;
                        logger.debug("Executing entry action for state: {}",
                                entryState.getName());
                        entryAction.doAction(event, entity, transition,
                                actionType);
                    }
                }
            }
        }
        catch (TransitionRollbackException e)
        {
            // Log and rethrow a rollback exception.
            logger.debug("Transition rolled back: state={} transition={} actionType={}",
                new Object[] {state.getName(), transition.getName(), actionType});
            throw e;
        }
        catch (TransitionFailureException e)
        {
            // Transition to the error state and rethrow the exception.
            logger.debug("Transition failed: state={} transition={}  actionType={}",
                    new Object[] {state.getName(),  transition.getName(), actionType});

            State<ET> errorState = map.getErrorState();

            // Make sure we have an error state!
            if (errorState == null)
            {
                String msg = "Attempt to throw TransitionFailureException when no error state exists";
                logger.error(msg, e);
                throw new FiniteStateException(msg, e);
            }

            // Now transition to it or try to at least.
            try
            {
                Action<ET> errorStateEntryAction = errorState.getEntryAction();
                if (errorStateEntryAction != null)
                {
                    logger.debug("Executing entry action for error state: {}",
                                     errorState.getName());
                    errorStateEntryAction.doAction(event, entity, transition,
                            Action.ENTER_ACTION);
                }
                nextState = errorState;
            }
            catch (Throwable t)
            {
                // This bad. Nothing to do but throw an generic exception.
                throw new FiniteStateException(
                        "Transition to error state failed", t);
            }
            // Store so that the application sees there has been an error.
            deferredException = e;
        }

        // If we changed state, move to the new state and notify listeners.
        if (state != nextState)
        {
            logger.debug("Entering new state: {}", nextState.getName());

            State<ET> prevState = state;
            state = nextState;

            for (StateChangeListener<ET> listener : listeners)
            {
                listener.stateChanged(entity, prevState, nextState);
            }

            if (isForwardChainEnabled())
            {
                // Now see if we have a chained transition to handle. We can
                // expect to get
                // a FiniteStateException since we may not have a chained
                // transition.
                try
                {
                    if ((transition = map.nextTransition(state, event, entity)) != null)
                    {
                        applyEvent(event);
                    }
                }
                catch (FiniteStateException f)
                {
                    // Just ignore it.
                    return;
                }
            }
        }

        // If we have a deferred exception, throw it now.
        if (deferredException != null)
            throw deferredException;
    }
