  public void endElement(String nsURI, String localName, String qname) {
    if (isBuffering()) {
      // set field of an RSS feed or RSS item
      ((ContentSetter) setter).set(buffer.toString());

      // clear buffer
      buffer = null;
    } else if (RSS_ITEM.equals(qname)) {
      feed.addItem(item);

      // (re)enter <channel> scope
      item = null;
    }
  }
