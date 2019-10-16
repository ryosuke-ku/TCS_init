  public T get(K key, String[] fields) {
    LOG.debug("get("+key+","+fields+")");
    if (fields==null)
      return infinispanClient.get(key);

    InfinispanQuery<K, T> query = new InfinispanQuery<K, T>(this);
    query.setKey(key);
    query.setFields(fields);
    query.build();


    Result<K,T> result = query.execute();
    try {
      result.next();
      return result.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
