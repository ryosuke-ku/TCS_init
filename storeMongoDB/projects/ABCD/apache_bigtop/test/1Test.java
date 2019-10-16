  public void wikipediaGraphDiskCacheTest() throws Exception {
    System.out.println();
    System.out.println("Starting wikipediaGraphDiskCacheTest");
    
    datafu.linkanalysis.PageRank graph = new datafu.linkanalysis.PageRank();
    
    String[] edges = getWikiExampleEdges();
    
    graph.enableEdgeDiskCaching();
    graph.setEdgeCachingThreshold(5);
    
    Map<String,Integer> nodeIdsMap = loadGraphFromEdgeList(graph, edges);
    
    assert graph.isUsingEdgeDiskCache() : "Expected disk cache to be used";
    
    // Without dangling node handling we will not get the true page rank since the total rank will
    // not add to 1.0.  Without dangling node handling some of the page rank drains out of the graph.
    graph.enableDanglingNodeHandling();
    
    performIterations(graph, 150, 1e-18f);
    
    String[] expectedRanks = getWikiExampleExpectedRanks();
    
    Map<String,Float> expectedRanksMap = parseExpectedRanks(expectedRanks);
    
    validateExpectedRanks(graph, nodeIdsMap, expectedRanksMap);
  }
