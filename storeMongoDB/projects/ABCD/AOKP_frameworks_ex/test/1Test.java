    protected void tearDown() throws Exception {
        // I explicitly want to do super's tear-down first, because I need to get it to reset
        // the media player before I can be confident that I can shut down the executor service.
        super.tearDown();
        mExecutor.shutdown();
        if (!mExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
            Log.e(TAG, "Couldn't shut down Executor during test, check your cleanup code!");
        }
        mExecutor = null;
    }
