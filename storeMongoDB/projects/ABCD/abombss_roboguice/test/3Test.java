    public void testRegistrationClear(){
        Context contextTwo = EasyMock.createMock(Context.class);

        for(Method method : eventOneMethods){
            eventManager.registerObserver(context, tester, method, EventOne.class);
        }
        for(Method method : eventOneMethods){
            eventManager.registerObserver(contextTwo, tester, method, EventOne.class);
        }

        eventManager.clear(context);

        eventManager.fire(context, event);
        tester.verifyCallCount(eventOneMethods, EventOne.class, 0);

        eventManager.fire(contextTwo, event);
        tester.verifyCallCount(eventOneMethods, EventOne.class, 1);
    }
