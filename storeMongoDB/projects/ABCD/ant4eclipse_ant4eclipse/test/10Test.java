  public void isFile() throws IOException {

    File testFile = File.createTempFile("a4e-testIsFile", null);
    File nonexistingDir = new File(testFile.getParentFile(), testFile.getName() + ".dir");
    System.out.println("Using temp. testfile: " + testFile.getAbsolutePath());
    testFile.deleteOnExit();

    Assure.isFile("bibo", testFile);

    try {
      Assure.isFile("bibo", testFile.getParentFile());
    } catch (Ant4EclipseException ex) {
      Assert.assertEquals(CoreExceptionCode.PRECONDITION_VIOLATION, ex.getExceptionCode());
    }

    try {
      Assure.isFile("bibo", nonexistingDir);
    } catch (Ant4EclipseException ex) {
      Assert.assertEquals(CoreExceptionCode.PRECONDITION_VIOLATION, ex.getExceptionCode());
    }

    try {
      Assure.isFile("bibo", new File("NICHT_DA"));
    } catch (Ant4EclipseException ex) {
      Assert.assertEquals(CoreExceptionCode.PRECONDITION_VIOLATION, ex.getExceptionCode());
    }

  }
