  public void testAllMatches() {
    doTestAllMatches(CharMatcher.ANY, "blah");
    doTestAllMatches(isNot('a'), "bcde");
    doTestAllMatches(is('a'), "aaaa");
    doTestAllMatches(noneOf("CharMatcher"), "zxqy");
    doTestAllMatches(anyOf("x"), "xxxx");
    doTestAllMatches(anyOf("xy"), "xyyx");
    doTestAllMatches(anyOf("CharMatcher"), "ChMa");
    doTestAllMatches(inRange('m', 'p'), "mom");
    doTestAllMatches(forPredicate(Predicates.equalTo('c')), "ccc");
    doTestAllMatches(CharMatcher.DIGIT, "0123456789\u0ED0\u1B59");
    doTestAllMatches(CharMatcher.JAVA_DIGIT, "0123456789");
    doTestAllMatches(CharMatcher.DIGIT.and(CharMatcher.ASCII), "0123456789");
    doTestAllMatches(CharMatcher.SINGLE_WIDTH, "\t0123ABCdef~\u00A0\u2111");
  }
