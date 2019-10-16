  private void reallyTestEmpty(CharMatcher matcher) throws Exception {
    assertEquals(-1, matcher.indexIn(""));
    assertEquals(-1, matcher.indexIn("", 0));
    try {
      matcher.indexIn("", 1);
      fail();
    } catch (IndexOutOfBoundsException expected) {
    }
    try {
      matcher.indexIn("", -1);
      fail();
    } catch (IndexOutOfBoundsException expected) {
    }
    assertEquals(-1, matcher.lastIndexIn(""));
    assertFalse(matcher.matchesAnyOf(""));
    assertTrue(matcher.matchesAllOf(""));
    assertTrue(matcher.matchesNoneOf(""));
    assertEquals("", matcher.removeFrom(""));
    assertEquals("", matcher.replaceFrom("", 'z'));
    assertEquals("", matcher.replaceFrom("", "ZZ"));
    assertEquals("", matcher.trimFrom(""));
    assertEquals(0, matcher.countIn(""));
  }
