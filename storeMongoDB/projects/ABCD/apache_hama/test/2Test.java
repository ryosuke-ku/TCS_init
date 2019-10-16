  public void simpleSpMVTest() {
    HamaConfiguration conf = new HamaConfiguration();
    String testDir = "/simple/";
    int size = 4;
    String matrixPath = baseDir + testDir + "inputMatrix";
    String vectorPath = baseDir + testDir + "inputVector";
    String outputPath = baseDir + testDir;

    try {
      if (fs.exists(new Path(baseDir))) {
        fs.delete(new Path(baseDir), true);
      }

      // creating test matrix
      HashMap<Integer, Writable> inputMatrix = new HashMap<Integer, Writable>();
      SparseVectorWritable vector0 = new SparseVectorWritable();
      vector0.setSize(size);
      vector0.addCell(0, 1);
      vector0.addCell(2, 6);
      SparseVectorWritable vector1 = new SparseVectorWritable();
      vector1.setSize(size);
      vector1.addCell(1, 4);
      SparseVectorWritable vector2 = new SparseVectorWritable();
      vector2.setSize(size);
      vector2.addCell(1, 2);
      vector2.addCell(2, 3);
      SparseVectorWritable vector3 = new SparseVectorWritable();
      vector3.setSize(size);
      vector3.addCell(0, 3);
      vector3.addCell(3, 5);
      inputMatrix.put(0, vector0);
      inputMatrix.put(1, vector1);
      inputMatrix.put(2, vector2);
      inputMatrix.put(3, vector3);
      writeMatrix(matrixPath, conf, inputMatrix);

      HashMap<Integer, Writable> inputVector = new HashMap<Integer, Writable>();
      DenseVectorWritable vector = new DenseVectorWritable();
      vector.setSize(size);
      vector.addCell(0, 2);
      vector.addCell(1, 3);
      vector.addCell(2, 6);
      vector.addCell(3, 1);
      inputVector.put(0, vector);
      writeMatrix(vectorPath, conf, inputVector);

      SpMV.main(new String[] { matrixPath, vectorPath, outputPath, "4" });

      String resultPath = SpMV.getResultPath();
      DenseVectorWritable result = new DenseVectorWritable();
      SpMV.readFromFile(resultPath, result, conf);
      LOG.info("result is a file: " + fs.isFile(new Path(resultPath)));

      double expected[] = { 38, 12, 24, 11 };
      if (result.getSize() != size)
        throw new Exception("Incorrect size of output vector");
      for (int i = 0; i < result.getSize(); i++)
        if ((result.get(i) - expected[i]) < 0.01)
          expected[i] = 0;

      for (int i = 0; i < expected.length; i++)
        if (expected[i] != 0)
          throw new Exception("Result doesn't meets expectations");

      fs.delete(new Path(baseDir), true);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getLocalizedMessage());
    } finally {
    }
  }
