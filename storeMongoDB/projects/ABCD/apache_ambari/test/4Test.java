  public void testInputFile_process3Rows() throws Exception {
    LOG.info("testInputFile_process3Rows()");

    File checkPointDir = createCheckpointDir("process3_checkpoint");
    File testFile = createFile("process3.log");

    init(testFile.getAbsolutePath());

    InputMgr inputMgr = EasyMock.createStrictMock(InputMgr.class);
    EasyMock.expect(inputMgr.getCheckPointFolderFile()).andReturn(checkPointDir);
    EasyMock.replay(inputMgr);
    inputFile.setInputMgr(inputMgr);

    inputFile.isReady();
    inputFile.start();

    assertEquals("Amount of the rows is incorrect", rows.size(), 3);
    for (int row = 0; row < 3; row++)
      assertEquals("Row #" + (row + 1) + " not correct", TEST_LOG_FILE_ROWS[row], rows.get(row));

    EasyMock.verify(inputMgr);
  }
