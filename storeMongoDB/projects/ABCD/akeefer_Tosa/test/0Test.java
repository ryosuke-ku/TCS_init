  public void testCallWithNullTokenHandlerThrowsIllegalArgumentException() {
    try {
      StringSubstituter.substitute("input", null);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException e) {
      // Expected
    }
  }
