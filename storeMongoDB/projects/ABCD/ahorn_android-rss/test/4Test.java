  public void isBufferingCategory() {
    assertFalse(handler.isBuffering());
    handler.startElement(null, null, "category", null);
    assertTrue(handler.isBuffering());
  }
