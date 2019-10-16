  public void shouldIncrementSpooledEventsCount() {

    final PrintWriter spoolWriter = mock(PrintWriter.class);
    spoolWriter.println("log event");

    final File mockFile = setupInputFileExpectations();
    LogSpoolerContext logSpoolerContext = new LogSpoolerContext(mockFile);
    logSpoolerContext.logEventSpooled();
    expect(rolloverCondition.shouldRollover(
        cmp(logSpoolerContext, new LogSpoolerEventCountComparator(), LogicalOperator.EQUAL))).
        andReturn(false);

    replay(spoolWriter, rolloverCondition, mockFile);

    LogSpooler logSpooler = new LogSpooler(spoolDirectory, SOURCE_FILENAME_PREFIX,
        rolloverCondition, rolloverHandler) {
      @Override
      protected PrintWriter initializeSpoolWriter(File spoolFile) throws IOException {
        return spoolWriter;
      }

      @Override
      protected File initializeSpoolFile() {
        return mockFile;
      }
    };
    logSpooler.add("log event");

    verify(rolloverCondition);
  }
