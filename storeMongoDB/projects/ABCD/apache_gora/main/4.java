  public Query<K, T> newQuery() {
    return new MemQuery<>(this);
  }
