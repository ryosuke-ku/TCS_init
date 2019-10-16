  public T get(K key, String[] fields) {
    T obj = (T) map.get(key);
    if (obj == null) {
      return null;
    }
    return getPersistent(obj, getFieldsToQuery(fields));
  }
