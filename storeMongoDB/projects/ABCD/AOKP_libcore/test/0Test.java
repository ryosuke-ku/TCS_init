    public void test_SamplingProfiler() throws Exception {
        ThreadSet threadSet = SamplingProfiler.newArrayThreadSet(Thread.currentThread());
        SamplingProfiler profiler = new SamplingProfiler(12, threadSet);
        profiler.start(100);
        toBeMeasured();
        profiler.stop();
        profiler.shutdown();
        test_HprofData(profiler.getHprofData(), true);
    }
