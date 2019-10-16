  public void items() {
    assertFalse(handler.feed().getItems().iterator().hasNext());

    final char[][] titles = { { 'a', 'b', 'c' }, { '1', '2', '3' } };
    for (char[] title : titles) {
      handler.startElement(null, null, "item", null);
      handler.startElement(null, null, "title", null);
      handler.characters(title, 0, 3);
      handler.endElement(null, null, "title");
      handler.endElement(null, null, "item");
    }

    final Iterator<RSSItem> items = handler.feed().getItems().iterator();
    assertTrue(items.hasNext());
    assertEquals("abc", items.next().getTitle());
    assertEquals("123", items.next().getTitle());
    assertFalse(items.hasNext());
  }
