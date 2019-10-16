  public CharMatcher negate() {
    final CharMatcher original = this;
    return new CharMatcher() {
      @Override public boolean matches(char c) {
        return !original.matches(c);
      }

      @Override public boolean matchesAllOf(CharSequence sequence) {
        return original.matchesNoneOf(sequence);
      }

      @Override public boolean matchesNoneOf(CharSequence sequence) {
        return original.matchesAllOf(sequence);
      }

      @Override public int countIn(CharSequence sequence) {
        return sequence.length() - original.countIn(sequence);
      }

      @Override public CharMatcher negate() {
        return original;
      }
    };
  }
