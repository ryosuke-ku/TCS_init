  public void characters(char ch[], int start, int length) {
    if (isBuffering()) {
      buffer.append(ch, start, length);
    }
  }
