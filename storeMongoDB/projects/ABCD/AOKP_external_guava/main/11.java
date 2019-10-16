  public Splitter limit(int limit) {
    checkArgument(limit > 0, "must be greater than zero: %s", limit);
    return new Splitter(strategy, omitEmptyStrings, trimmer, limit);
  }
