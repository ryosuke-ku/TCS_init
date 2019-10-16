  public void testAtEachSplitEmptyStringWithOmitEmptyStrings() {
    ASSERT.that(Splitter.fixedLength(3).omitEmptyStrings().split("")).isEmpty();
  }
