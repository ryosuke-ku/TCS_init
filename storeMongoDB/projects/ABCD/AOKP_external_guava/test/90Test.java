  private void doTestTrimAndCollapse(String in, String out) {
    // Try a few different matchers which all match '-' and not 'x'
    assertEquals(out, is('-').trimAndCollapseFrom(in, '_'));
    assertEquals(out, is('-').or(is('#')).trimAndCollapseFrom(in, '_'));
    assertEquals(out, isNot('x').trimAndCollapseFrom(in, '_'));
    assertEquals(out, is('x').negate().trimAndCollapseFrom(in, '_'));
    assertEquals(out, anyOf("-").trimAndCollapseFrom(in, '_'));
    assertEquals(out, anyOf("-#").trimAndCollapseFrom(in, '_'));
    assertEquals(out, anyOf("-#123").trimAndCollapseFrom(in, '_'));
  }
