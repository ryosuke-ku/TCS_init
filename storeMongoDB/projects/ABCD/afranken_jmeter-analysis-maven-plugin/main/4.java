  public Quantile getQuantiles(int q) {
    assertFinished();

    if( histogram == null ) {
      throw new IllegalStateException("No histogram available");
    }

    return new Quantile(q, histogram.values());
  }
