  public void testTrimFrom() {
    // trimming -
    doTestTrimFrom("-", "");
    doTestTrimFrom("x-", "x");
    doTestTrimFrom("-x", "x");
    doTestTrimFrom("--", "");
    doTestTrimFrom("x--", "x");
    doTestTrimFrom("--x", "x");
    doTestTrimFrom("-x-", "x");
    doTestTrimFrom("x-x", "x-x");
    doTestTrimFrom("---", "");
    doTestTrimFrom("--x-", "x");
    doTestTrimFrom("--xx", "xx");
    doTestTrimFrom("-x--", "x");
    doTestTrimFrom("-x-x", "x-x");
    doTestTrimFrom("-xx-", "xx");
    doTestTrimFrom("x--x", "x--x");
    doTestTrimFrom("x-x-", "x-x");
    doTestTrimFrom("x-xx", "x-xx");
    doTestTrimFrom("x-x--xx---x----x", "x-x--xx---x----x");
    // additional testing using the doc example
    assertEquals("cat", anyOf("ab").trimFrom("abacatbab"));
  }
