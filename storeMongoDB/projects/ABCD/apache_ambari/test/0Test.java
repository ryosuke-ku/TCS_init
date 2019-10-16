  public void shouldCloseCurrentSpoolFileOnRollOver() {
    final PrintWriter spoolWriter = mock(PrintWriter.class);
    spoolWriter.println("log event");
    spoolWriter.flush();
    spoolWriter.close();

    final File mockFile = setupInputFileExpectations();
    LogSpoolerContext logSpoolerContext = new LogSpoolerContext(mockFile);
    expect(rolloverCondition.shouldRollover(
        cmp(logSpoolerContext, new LogSpoolerFileComparator(), LogicalOperator.EQUAL))).
        andReturn(true);
    rolloverHandler.handleRollover(mockFile);

    replay(spoolWriter, rolloverCondition, rolloverHandler, mockFile);

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

    verify(spoolWriter);
  }

        return compressedFile;
      }
    };
    s3Uploader.uploadFile(fileToUpload, LOG_TYPE);

    verify(s3Util);
  }
