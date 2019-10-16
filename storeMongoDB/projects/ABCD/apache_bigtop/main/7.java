  public float nextIteration(ProgressIndicator progressIndicator) throws IOException
  {
    distribute(progressIndicator);
    commit(progressIndicator);
    
    return getTotalRankChange();
  }
