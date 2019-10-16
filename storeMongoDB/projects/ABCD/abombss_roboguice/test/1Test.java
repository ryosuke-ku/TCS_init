    public void testRegistrationLifeCycle(){
        for(Method method : eventOneMethods){
            eventManager.registerObserver(context, tester, method, EventOne.class);
        }
        for(Method method : eventTwoMethods){
            eventManager.registerObserver(context, tester, method, EventTwo.class);
        }

        eventManager.fire(context, event);

        tester.verifyCallCount(eventOneMethods, EventOne.class, 1);
        tester.verifyCallCount(eventTwoMethods, EventTwo.class, 0);

        //reset
        tester.reset();

        eventManager.unregisterObserver(context, tester, EventOne.class);
        eventManager.unregisterObserver(context, tester, EventTwo.class);

        eventManager.fire(context, event);

        tester.verifyCallCount(eventOneMethods, EventOne.class, 0);
        tester.verifyCallCount(eventTwoMethods, EventTwo.class, 0);
    }
