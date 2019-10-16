  public void testSplitterIterableIsUnmodifiable_char() {
    assertIteratorIsUnmodifiable(COMMA_SPLITTER.split("a,b").iterator());
  }
