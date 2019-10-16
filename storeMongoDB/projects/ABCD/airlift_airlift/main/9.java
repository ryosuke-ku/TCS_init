    public void addValue(Duration duration)
    {
        sample.update(duration.toMillis());
        sum.addAndGet(duration.toMillis());
        count.incrementAndGet();
    }
