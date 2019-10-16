  public static CharMatcher forPredicate(final Predicate<? super Character> predicate) {
    checkNotNull(predicate);
    if (predicate instanceof CharMatcher) {
      return (CharMatcher) predicate;
    }
    return new CharMatcher() {
      @Override public boolean matches(char c) {
        return predicate.apply(c);
      }

      @Override public boolean apply(Character character) {
        return predicate.apply(checkNotNull(character));
      }
    };
  }
