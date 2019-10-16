    @Override public CharMatcher or(CharMatcher other) {
      List<CharMatcher> newComponents = new ArrayList<CharMatcher>(components);
      newComponents.add(checkNotNull(other));
      return new Or(newComponents);
    }
