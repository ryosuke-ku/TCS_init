  public float getNodeRank(int nodeId)
  {
    int nodeIndex = this.nodeIndices.get(nodeId);
    return nodeData.get(nodeIndex);
  }
