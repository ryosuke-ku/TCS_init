  private void doTestTrimLeadingFrom(String in, String out) {
    // Try a few different matchers which all match '-' and not 'x'
    assertEquals(out, is('-').trimLeadingFrom(in));
    assertEquals(out, is('-').or(is('#')).trimLeadingFrom(in));
    assertEquals(out, isNot('x').trimLeadingFrom(in));
    assertEquals(out, is('x').negate().trimLeadingFrom(in));
    assertEquals(out, anyOf("-#").trimLeadingFrom(in));
    assertEquals(out, anyOf("-#123").trimLeadingFrom(in));
  }
