  private void performIterations(datafu.linkanalysis.PageRank graph, int maxIters, float tolerance) throws IOException
  {
    System.out.println(String.format("Beginning iteration (maxIters = %d, tolerance=%e)", maxIters, tolerance));
    
    datafu.linkanalysis.PageRank.ProgressIndicator progressIndicator = getDummyProgressIndicator();
    
    System.out.println("Initializing graph");
    long startTime = System.nanoTime();
    graph.init(progressIndicator);
    System.out.println(String.format("Done, took %f ms", (System.nanoTime() - startTime)/10.0e6));
    
    float totalDiff;
    int iter = 0;
    
    System.out.println("Beginning iterations");
    startTime = System.nanoTime();
    do 
    {
      totalDiff = graph.nextIteration(progressIndicator);
      iter++;      
    } while(iter < maxIters && totalDiff > tolerance);
    System.out.println(String.format("Done, took %f ms", (System.nanoTime() - startTime)/10.0e6));
  }
