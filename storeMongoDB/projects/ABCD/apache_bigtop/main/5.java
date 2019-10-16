  public void addEdges(Integer sourceId, ArrayList<Map<String,Object>> sourceEdges) throws IOException
  {
    int source = sourceId.intValue();
   
    maybeCreateNode(source);
    
    if (this.shouldCacheEdgesOnDisk && !usingEdgeDiskCache && (sourceEdges.size() + this.edgeCount) >= this.edgeCachingThreshold)
    {
      writeEdgesToDisk();
    }
    
    // store the source node id itself
    appendEdgeData(source);
    
    // store how many outgoing edges this node has
    appendEdgeData(sourceEdges.size());
    
    // store the outgoing edges
    for (Map<String,Object> edge : sourceEdges)
    {
      int dest = ((Integer)edge.get("dest")).intValue();
      float weight = ((Double)edge.get("weight")).floatValue();
            
      maybeCreateNode(dest);
      
      appendEdgeData(dest);
      
      // location of weight in weights array
      appendEdgeData(Math.max(1, (int)(weight * EDGE_WEIGHT_MULTIPLIER)));
      
      this.edgeCount++;
    }
  }
