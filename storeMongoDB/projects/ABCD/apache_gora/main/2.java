  public List<PartitionQuery<K, T>> getPartitions(Query<K, T> query)
      throws IOException {
    LOG.debug("getPartitions()");

    // 1 - split the query per location
    List<PartitionQuery<K,T>> locations = ((InfinispanQuery<K,T>)query).split();

    // 2 -split each location
    List<PartitionQuery<K,T>> splitLocations = new ArrayList<>();
    for(PartitionQuery<K,T> location : locations) {

      LOG.trace("location: "+ ((InfinispanQuery<K, T>)location).getLocation().toString());

      // 2.1 - compute the result size
      InfinispanQuery<K,T> sizeQuery = (InfinispanQuery<K, T>) ((InfinispanQuery<K, T>) location).clone();
      sizeQuery.setFields(primaryFieldName);
      sizeQuery.setLimit(1);
      sizeQuery.rebuild();

      // 2.2 - check if splitting is necessary
      int resultSize = sizeQuery.getResultSize();
      long queryLimit = query.getLimit();
      long splitLimit = queryLimit>0 ? Math.min((long)resultSize,queryLimit) : resultSize;
      LOG.trace("split limit: "+ splitLimit);
      LOG.trace("split size: "+ splitSize);
      if (splitLimit <= splitSize) {
        LOG.trace("location returned");
        splitLocations.add(location);
        continue;
      }

      // 2.3 - compute the splits
      for(int i=0; i<Math.ceil((double)splitLimit/(double)splitSize); i++) {
        InfinispanQuery<K, T> split = (InfinispanQuery<K, T>) ((InfinispanQuery<K, T>) location).clone();
        split.setOffset(i * splitSize);
        split.setLimit(splitSize);
        split.rebuild();
        splitLocations.add(split);
      }
    }

    return splitLocations;
  }
