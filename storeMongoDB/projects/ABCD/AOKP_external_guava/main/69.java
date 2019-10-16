    @Override public CharMatcher and(CharMatcher other) {
      List<CharMatcher> newComponents = new ArrayList<CharMatcher>(components);
      newComponents.add(checkNotNull(other));
      return new And(newComponents);
    }
