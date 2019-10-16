  public void testFloat() throws IOException {
    
    ArrayList<Float> testData = new ArrayList<>();
    testData.add(Float.NEGATIVE_INFINITY);
    testData.add(Float.MIN_VALUE);
    testData.add(Math.nextUp(Float.NEGATIVE_INFINITY));
    testData.add((float) Math.pow(10.0f, 30.0f) * -1.0f);
    testData.add((float) Math.pow(10.0f, 30.0f));
    testData.add((float) Math.pow(10.0f, -30.0f) * -1.0f);
    testData.add((float) Math.pow(10.0f, -30.0f));
    testData.add(Math.nextAfter(0.0f, Float.NEGATIVE_INFINITY));
    testData.add(0.0f);
    testData.add(Math.nextAfter(Float.MAX_VALUE, Float.NEGATIVE_INFINITY));
    testData.add(Float.MAX_VALUE);
    testData.add(Float.POSITIVE_INFINITY);
    
    Collections.sort(testData);
    
    SignedBinaryEncoder encoder = new SignedBinaryEncoder();

    for (int i = 0; i < testData.size(); i++) {
      byte[] enc = encoder.encodeFloat(testData.get(i));
      assertEquals(testData.get(i), (Float)encoder.decodeFloat(enc));
      if (i > 1) {
        assertTrue("Checking " + testData.get(i) + " > " + testData.get(i - 1),
            new Text(enc).compareTo(new Text(encoder.encodeFloat(testData.get(i - 1)))) > 0);
      }
    }
  }
