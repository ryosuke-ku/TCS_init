  private Map<String,Integer> loadGraphFromEdgeList(datafu.linkanalysis.PageRank graph, String[] edges) throws IOException
  {
    Map<Integer,ArrayList<Map<String,Object>>> nodeEdgesMap = new HashMap<Integer,ArrayList<Map<String,Object>>>();
    Map<String,Integer> nodeIdsMap = new HashMap<String,Integer>();
    
    for (String edge : edges)
    {
      String[] parts = edge.split(" ");
      assert parts.length == 2 : "Expected two parts";
      
      int sourceId = getOrCreateId(parts[0], nodeIdsMap);
      int destId = getOrCreateId(parts[1], nodeIdsMap);
      
      Map<String,Object> edgeMap = new HashMap<String,Object>();
      edgeMap.put("weight", 1.0);
      edgeMap.put("dest", destId);
      
      ArrayList<Map<String,Object>> nodeEdges = null;
      
      if (nodeEdgesMap.containsKey(sourceId))
      {
        nodeEdges = nodeEdgesMap.get(sourceId);
      }
      else
      {
        nodeEdges = new ArrayList<Map<String,Object>>();
        nodeEdgesMap.put(sourceId, nodeEdges);
      }
      
      nodeEdges.add(edgeMap);
    }
    
    for (Map.Entry<Integer, ArrayList<Map<String,Object>>> e : nodeEdgesMap.entrySet())
    {
      graph.addEdges(e.getKey(), e.getValue());
    }
    
    return nodeIdsMap;
  }
