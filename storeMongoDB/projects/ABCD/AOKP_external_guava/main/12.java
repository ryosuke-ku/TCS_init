  public Splitter trimResults(CharMatcher trimmer) {
    checkNotNull(trimmer);
    return new Splitter(strategy, omitEmptyStrings, trimmer, limit);
  }
