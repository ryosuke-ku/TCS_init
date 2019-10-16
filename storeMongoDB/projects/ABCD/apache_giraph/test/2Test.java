  public void testGenerateNameAndParseIt() {
    ComputationDoneName computationDoneName = new ComputationDoneName(7);
    assertEquals(7, computationDoneName.getWorkerId());

    ComputationDoneName parsedComputationDoneName =
        ComputationDoneName.fromName(computationDoneName.getName());
    assertEquals(parsedComputationDoneName.getName(),
        computationDoneName.getName());
    assertEquals(7, parsedComputationDoneName.getWorkerId());
  }
