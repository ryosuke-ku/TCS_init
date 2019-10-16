  public void testTrimTrailingFrom() {
    // trimming -
    doTestTrimTrailingFrom("-", "");
    doTestTrimTrailingFrom("x-", "x");
    doTestTrimTrailingFrom("-x", "-x");
    doTestTrimTrailingFrom("--", "");
    doTestTrimTrailingFrom("x--", "x");
    doTestTrimTrailingFrom("--x", "--x");
    doTestTrimTrailingFrom("-x-", "-x");
    doTestTrimTrailingFrom("x-x", "x-x");
    doTestTrimTrailingFrom("---", "");
    doTestTrimTrailingFrom("--x-", "--x");
    doTestTrimTrailingFrom("--xx", "--xx");
    doTestTrimTrailingFrom("-x--", "-x");
    doTestTrimTrailingFrom("-x-x", "-x-x");
    doTestTrimTrailingFrom("-xx-", "-xx");
    doTestTrimTrailingFrom("x--x", "x--x");
    doTestTrimTrailingFrom("x-x-", "x-x");
    doTestTrimTrailingFrom("x-xx", "x-xx");
    doTestTrimTrailingFrom("x-x--xx---x----x", "x-x--xx---x----x");
    // additional testing using the doc example
    assertEquals("abacat", anyOf("ab").trimTrailingFrom("abacatbab"));
  }
