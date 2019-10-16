  public void simpleSpMVTestFile() {
    try {
      int size = 4;
      String matrixPath = "";
      String vectorPath = "";
      String outputPath = "";
      if (matrixPath.isEmpty() || vectorPath.isEmpty() || outputPath.isEmpty()) {
        LOG.info("Please setup input path for vector and matrix and output path for result, "
            + "if you want to run this example");
        return;
      }
      SpMV.main(new String[] { matrixPath, vectorPath, outputPath, "4" });

      String resultPath = SpMV.getResultPath();
      DenseVectorWritable result = new DenseVectorWritable();
      SpMV.readFromFile(resultPath, result, conf);

      double expected[] = { 38, 12, 24, 11 };
      if (result.getSize() != size)
        throw new Exception("Incorrect size of output vector");
      for (int i = 0; i < result.getSize(); i++)
        if ((result.get(i) - expected[i]) < 0.01)
          expected[i] = 0;

      for (int i = 0; i < expected.length; i++)
        if (expected[i] != 0)
          throw new Exception("Result doesn't meets expectations");

    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getLocalizedMessage());
    }
  }
