  public void testInvalidZeroLimit() {
    try {
      COMMA_SPLITTER.limit(0);
      fail();
    } catch (IllegalArgumentException expected) {
    }
  }
