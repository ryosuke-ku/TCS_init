    public void testScheduler() throws Exception {
        TimeTravelScheduler scheduler = new TimeTravelScheduler();
        OperationScheduler.Options options = new OperationScheduler.Options();
        assertEquals(Long.MAX_VALUE, scheduler.getNextTimeMillis(options));
        assertEquals(0, scheduler.getLastSuccessTimeMillis());
        assertEquals(0, scheduler.getLastAttemptTimeMillis());

        long beforeTrigger = scheduler.timeMillis;
        scheduler.setTriggerTimeMillis(beforeTrigger + 1000000);
        assertEquals(beforeTrigger + 1000000, scheduler.getNextTimeMillis(options));

        // It will schedule for the later of the trigger and the moratorium...
        scheduler.setMoratoriumTimeMillis(beforeTrigger + 500000);
        assertEquals(beforeTrigger + 1000000, scheduler.getNextTimeMillis(options));
        scheduler.setMoratoriumTimeMillis(beforeTrigger + 1500000);
        assertEquals(beforeTrigger + 1500000, scheduler.getNextTimeMillis(options));

        // Test enable/disable toggle
        scheduler.setEnabledState(false);
        assertEquals(Long.MAX_VALUE, scheduler.getNextTimeMillis(options));
        scheduler.setEnabledState(true);
        assertEquals(beforeTrigger + 1500000, scheduler.getNextTimeMillis(options));

        // Backoff interval after an error
        long beforeError = (scheduler.timeMillis += 100);
        scheduler.onTransientError();
        assertEquals(0, scheduler.getLastSuccessTimeMillis());
        assertEquals(beforeError, scheduler.getLastAttemptTimeMillis());
        assertEquals(beforeTrigger + 1500000, scheduler.getNextTimeMillis(options));
        options.backoffFixedMillis = 1000000;
        options.backoffIncrementalMillis = 500000;
        assertEquals(beforeError + 1500000, scheduler.getNextTimeMillis(options));

        // Two errors: backoff interval increases
        beforeError = (scheduler.timeMillis += 100);
        scheduler.onTransientError();
        assertEquals(beforeError, scheduler.getLastAttemptTimeMillis());
        assertEquals(beforeError + 2000000, scheduler.getNextTimeMillis(options));

        // Reset transient error: no backoff interval
        scheduler.resetTransientError();
        assertEquals(0, scheduler.getLastSuccessTimeMillis());
        assertEquals(beforeTrigger + 1500000, scheduler.getNextTimeMillis(options));
        assertEquals(beforeError, scheduler.getLastAttemptTimeMillis());

        // Permanent error holds true even if transient errors are reset
        // However, we remember that the transient error was reset...
        scheduler.onPermanentError();
        assertEquals(Long.MAX_VALUE, scheduler.getNextTimeMillis(options));
        scheduler.resetTransientError();
        assertEquals(Long.MAX_VALUE, scheduler.getNextTimeMillis(options));
        scheduler.resetPermanentError();
        assertEquals(beforeTrigger + 1500000, scheduler.getNextTimeMillis(options));

        // Success resets the trigger
        long beforeSuccess = (scheduler.timeMillis += 100);
        scheduler.onSuccess();
        assertEquals(beforeSuccess, scheduler.getLastAttemptTimeMillis());
        assertEquals(beforeSuccess, scheduler.getLastSuccessTimeMillis());
        assertEquals(Long.MAX_VALUE, scheduler.getNextTimeMillis(options));

        // The moratorium is not reset by success!
        scheduler.setTriggerTimeMillis(0);
        assertEquals(beforeTrigger + 1500000, scheduler.getNextTimeMillis(options));
        scheduler.setMoratoriumTimeMillis(0);
        assertEquals(beforeSuccess, scheduler.getNextTimeMillis(options));

        // Periodic interval after success
        options.periodicIntervalMillis = 250000;
        scheduler.setTriggerTimeMillis(Long.MAX_VALUE);
        assertEquals(beforeSuccess + 250000, scheduler.getNextTimeMillis(options));

        // Trigger minimum is also since the last success
        options.minTriggerMillis = 1000000;
        assertEquals(beforeSuccess + 1000000, scheduler.getNextTimeMillis(options));
    }
