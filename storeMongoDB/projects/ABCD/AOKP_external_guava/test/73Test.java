  public void testPrecomputedOptimizations() {
    // These are testing behavior that's never promised by the API.
    // Some matchers are so efficient that it is a waste of effort to
    // build a precomputed version.
    CharMatcher m1 = is('x');
    assertSame(m1, m1.precomputed());

    CharMatcher m2 = anyOf("Az");
    assertSame(m2, m2.precomputed());

    CharMatcher m3 = inRange('A', 'Z');
    assertSame(m3, m3.precomputed());

    assertSame(CharMatcher.NONE, CharMatcher.NONE.precomputed());
    assertSame(CharMatcher.ANY, CharMatcher.ANY.precomputed());
  }
