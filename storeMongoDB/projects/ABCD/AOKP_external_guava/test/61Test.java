  public void testJavaIsoControl() {
    for (int c = 0; c <= Character.MAX_VALUE; c++) {
      assertEquals("" + c, Character.isISOControl(c),
          CharMatcher.JAVA_ISO_CONTROL.matches((char) c));
    }
  }
