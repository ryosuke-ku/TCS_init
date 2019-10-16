  public void testDotlessLowerI() {
      Priority level = Priority.toPriority("\u0131nfo");
      assertEquals("INFO", level.toString());
  }
