        synchronized void wait(int limit) throws InterruptedException {
            while (i > limit) {
                wait();
            }
        }
