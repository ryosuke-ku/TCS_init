  public void testCollapse_any() {
    assertEquals("", CharMatcher.ANY.collapseFrom("", '_'));
    assertEquals("_", CharMatcher.ANY.collapseFrom("a", '_'));
    assertEquals("_", CharMatcher.ANY.collapseFrom("ab", '_'));
    assertEquals("_", CharMatcher.ANY.collapseFrom("abcd", '_'));
  }
