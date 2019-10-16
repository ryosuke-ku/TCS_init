  public void testPatternSplitEmptyTokenOmitEmptyStrings() {
    String emptyToken = "a. .c";
    Iterable<String> letters = Splitter.on(literalDotPattern())
        .omitEmptyStrings().trimResults().split(emptyToken);
    ASSERT.that(letters).hasContentsInOrder("a", "c");
  }
