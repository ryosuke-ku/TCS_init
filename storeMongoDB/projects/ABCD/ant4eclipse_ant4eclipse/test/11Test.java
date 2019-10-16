  public void isDirectory() throws IOException {

    File testFile = File.createTempFile("a4e-testIsDirectory", null);
    testFile.deleteOnExit();
    System.out.println("Using temp. testfile: " + testFile.getAbsolutePath());

    Assure.isDirectory("dodo", testFile.getParentFile());

    try {
      Assure.isDirectory("dodo", testFile);
    } catch (Ant4EclipseException ex) {
      Assert.assertEquals(CoreExceptionCode.PRECONDITION_VIOLATION, ex.getExceptionCode());
    }

    try {
      Assure.isDirectory("dodo", new File("NICHT_DA"));
    } catch (Ant4EclipseException ex) {
      Assert.assertEquals(CoreExceptionCode.PRECONDITION_VIOLATION, ex.getExceptionCode());
    }

  }
