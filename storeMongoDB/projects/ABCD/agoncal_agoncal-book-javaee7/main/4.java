  public Integer getNumberOfItems() {
    if (cache == null || cache.isEmpty())
      return 0;
    return cache.size();
  }
