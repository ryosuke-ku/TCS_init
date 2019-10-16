  private void validateExpectedRanks(datafu.linkanalysis.PageRank graph, Map<String,Integer> nodeIds, Map<String,Float> expectedRanks)
  {
    System.out.println("Validating page rank results");
    
    for (Map.Entry<String,Integer> e : nodeIds.entrySet())
    {
      float rank = graph.getNodeRank(e.getValue());
      
      float expectedRank = expectedRanks.get(e.getKey());
      // require 0.1% accuracy
      assert (Math.abs(expectedRank - rank*100.0f) < 0.1) : String.format("Did not get expected rank for %s", e.getKey());      
    }
    
    System.out.println("All ranks match expected");
  }
