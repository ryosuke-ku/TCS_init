    public void stop() {
        if (sampler == null) {
            return;
        }
        synchronized(sampler) {
            sampler.stop = true;
            while (!sampler.stopped) {
                try {
                    sampler.wait();
                } catch (InterruptedException ignored) {
                }
            }
        }
        sampler = null;
    }
