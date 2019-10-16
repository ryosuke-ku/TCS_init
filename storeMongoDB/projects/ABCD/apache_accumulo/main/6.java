  public long getTimeout(TimeUnit timeUnit) {
    return timeUnit.convert(timeout != null ? timeout : DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
  }
