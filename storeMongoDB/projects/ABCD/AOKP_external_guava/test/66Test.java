  private void doTestTrimTrailingFrom(String in, String out) {
    // Try a few different matchers which all match '-' and not 'x'
    assertEquals(out, is('-').trimTrailingFrom(in));
    assertEquals(out, is('-').or(is('#')).trimTrailingFrom(in));
    assertEquals(out, isNot('x').trimTrailingFrom(in));
    assertEquals(out, is('x').negate().trimTrailingFrom(in));
    assertEquals(out, anyOf("-#").trimTrailingFrom(in));
    assertEquals(out, anyOf("-#123").trimTrailingFrom(in));
  }
