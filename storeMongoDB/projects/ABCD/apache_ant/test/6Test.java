    public void testNullThrowableMessageLog() {
        p.log(new Task() {}, null, new Throwable(), Project.MSG_ERR);
        // be content if no exception has been thrown
    }
