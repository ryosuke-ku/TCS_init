    public void concurrentRemove() throws Exception {
        final Foo foo = new Foo();

        int count = 0;
        foo.lock();
        try {
            for (; count < foo.strings.size(); count++) {
                if (count == 2) { // Two for no particular reason,..

                    // Concurrent remove of some element should fail with timeout, preventing deadlock
                    assertTrue(
                        Executors.newSingleThreadExecutor().submit(new Callable<Boolean>() {
                            public Boolean call() throws Exception {
                                try {
                                    Iterator<String> it = foo.getIterator();
                                    it.next();
                                    it.remove();
                                } catch (RuntimeException ex) {
                                    if ("Failed to acquire lock".equals(ex.getMessage()))
                                        return true; // All good, we should fail to get the lock
                                    ex.printStackTrace(System.err);
                                }
                                return false;
                            }
                        }).get()
                    );
                }
            }
        } finally {
            foo.unlock();
        }

        assertEquals(count, 3);
        assertEquals(foo.strings.size(), 3);
    }
