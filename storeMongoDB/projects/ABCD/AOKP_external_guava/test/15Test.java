  public void testMapSplitter_orderedResults() {
    Map<String, String> m = Splitter.on(",")
        .withKeyValueSeparator(":")
        .split("boy:tom,girl:tina,cat:kitty,dog:tommy");

    ASSERT.that(m.keySet()).hasContentsInOrder("boy", "girl", "cat", "dog");
    ASSERT.that(m).isEqualTo(
        ImmutableMap.of("boy", "tom", "girl", "tina", "cat", "kitty", "dog", "tommy"));

    // try in a different order
    m = Splitter.on(",")
        .withKeyValueSeparator(":")
        .split("girl:tina,boy:tom,dog:tommy,cat:kitty");

    ASSERT.that(m.keySet()).hasContentsInOrder("girl", "boy", "dog", "cat");
    ASSERT.that(m).isEqualTo(
        ImmutableMap.of("boy", "tom", "girl", "tina", "cat", "kitty", "dog", "tommy"));
  }
