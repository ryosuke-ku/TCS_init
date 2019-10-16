  public void init(ProgressIndicator progressIndicator) throws IOException
  {
    if (this.edgeDataOutputStream != null)
    {
      this.edgeDataOutputStream.close();
      this.edgeDataOutputStream = null;
    }
    
    // initialize all nodes to an equal share of the total rank (1.0)
    float nodeRank = 1.0f / this.nodeCount;        
    for (int j=0; j<this.nodeData.size(); j+=3)
    {
      nodeData.set(j, nodeRank);      
      progressIndicator.progress();
    }      
    
    Iterator<Integer> edgeData = getEdgeData();
    
    while(edgeData.hasNext())
    {
      int sourceId = edgeData.next();
      int nodeEdgeCount = edgeData.next();
      
      while (nodeEdgeCount-- > 0)
      {
        // skip the destination node id
        edgeData.next();
        
        float weight = edgeData.next();
                
        int nodeIndex = this.nodeIndices.get(sourceId);
        
        float totalWeight = this.nodeData.getFloat(nodeIndex+1); 
        totalWeight += weight;
        this.nodeData.set(nodeIndex+1, totalWeight);
        
        progressIndicator.progress();
      }
    }
    
    // if handling dangling nodes, get a list of them by finding those nodes with no outgoing
    // edges (i.e. total outgoing edge weight is 0.0)
    if (shouldHandleDanglingNodes)
    {
      for (Map.Entry<Integer,Integer> e : nodeIndices.entrySet())
      {
        int nodeId = e.getKey();
        int nodeIndex = e.getValue();
        float totalWeight = nodeData.getFloat(nodeIndex+1);
        if (totalWeight == 0.0f)
        {
          danglingNodes.add(nodeId);
        }
      }
    }
  }
