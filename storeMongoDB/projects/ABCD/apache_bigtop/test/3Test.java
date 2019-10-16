  public void hubAndSpokeInMemoryTest() throws Exception {
    System.out.println();
    System.out.println("Starting hubAndSpokeInMemoryTest");
    
    datafu.linkanalysis.PageRank graph = new datafu.linkanalysis.PageRank();
   
    String[] edges = getHubAndSpokeEdges();
    
    Map<String,Integer> nodeIdsMap = loadGraphFromEdgeList(graph, edges);
    
    graph.enableDanglingNodeHandling();
    
    performIterations(graph, 150, 1e-18f);
    
    // no need to validate, this is just a perf test for runtime comparison
  }
