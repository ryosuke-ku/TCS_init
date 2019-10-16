  public void inRange() {

    Assure.inRange(5, 1, 10);
    Assure.inRange(1, 1, 10);
    Assure.inRange(10, 1, 10);

    try {
      Assure.inRange(0, 1, 10);
    } catch (Ant4EclipseException ex) {
      Assert.assertEquals(CoreExceptionCode.PRECONDITION_VIOLATION, ex.getExceptionCode());
    }

    try {
      Assure.inRange(11, 1, 10);
    } catch (Ant4EclipseException ex) {
      Assert.assertEquals(CoreExceptionCode.PRECONDITION_VIOLATION, ex.getExceptionCode());
    }
  }
