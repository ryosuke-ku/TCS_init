  private void reallyTestOneCharNoMatch(CharMatcher matcher, String s) {
    assertFalse(matcher.matches(s.charAt(0)));
    assertFalse(matcher.apply(s.charAt(0)));
    assertEquals(-1, matcher.indexIn(s));
    assertEquals(-1, matcher.indexIn(s, 0));
    assertEquals(-1, matcher.indexIn(s, 1));
    assertEquals(-1, matcher.lastIndexIn(s));
    assertFalse(matcher.matchesAnyOf(s));
    assertFalse(matcher.matchesAllOf(s));
    assertTrue(matcher.matchesNoneOf(s));

    // Note: only 'assertEquals' is promised by the API.  Although they could
    // have been assertSame() on the server side, they have to be assertEquals
    // in GWT, because of GWT issue 4491.
    assertEquals(s, matcher.removeFrom(s));
    assertEquals(s, matcher.replaceFrom(s, 'z'));
    assertEquals(s, matcher.replaceFrom(s, "ZZ"));
    assertEquals(s, matcher.trimFrom(s));
    assertEquals(0, matcher.countIn(s));
  }
