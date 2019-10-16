  public @Nonnull String toString() {
    return concat(String.format("%s[button=%s, times=%d]", getClass().getName(), button.toString(), times));
  }
