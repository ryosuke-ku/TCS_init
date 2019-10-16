  public void testAggregate2() {

    Samples samples = new Samples(2, false);

    samples.addSample(10, 2);
    samples.addSample(20, 4);
    samples.addSample(30, 6);
    samples.addSample(40, 8);

    // first compression has taken place
    // result is: (15, 3) and (35, 7). compression=2

    samples.addSample(50, 10);

    // second compression has taken place
    // result is: (25, 5) and a pending element. compression=4

    samples.addSample(60, 12);
    samples.addSample(100, 200);


    samples.finish();


    assertEquals("samples", Arrays.asList(5L, 222/3L), samples.getSamples());
    assertEquals("timestamps", Arrays.asList(25L, 75L), samples.getTimestamps());
  }
