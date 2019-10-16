  public Splitter omitEmptyStrings() {
    return new Splitter(strategy, true, trimmer, limit);
  }
