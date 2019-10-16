    public HazelcastInstance getInstance() throws InterruptedException {
        if (instance == null) {
            initializationLatch.await();
            this.instance = buildInstance();
            instanceLatch.countDown();
        }
        return instance;
    }
