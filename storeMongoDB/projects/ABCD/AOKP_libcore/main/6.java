    public HprofData getHprofData() {
        if (sampler != null) {
            throw new IllegalStateException("cannot access hprof data while sampling");
        }
        return hprofData;
    }
