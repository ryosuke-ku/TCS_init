  public synchronized T get() {
    if (iterator.hasNext()) {
      return iterator.next();
    } else {
      iterator = elements.iterator();
      return iterator.next();
    }
  }
