    public StateTransitionLatch<ET> createStateTransitionLatch(State<ET> expected,
            boolean exitOnError)
    {
        return new StateTransitionLatch<ET>(this, expected, exitOnError);
    }
