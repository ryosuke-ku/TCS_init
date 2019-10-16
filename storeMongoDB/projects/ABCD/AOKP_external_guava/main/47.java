    @Override public boolean equals(@Nullable Object obj) {
      if (obj instanceof ContainsPatternPredicate) {
        ContainsPatternPredicate that = (ContainsPatternPredicate) obj;

        // Pattern uses Object (identity) equality, so we have to reach
        // inside to compare individual fields.
        return Objects.equal(pattern.pattern(), that.pattern.pattern())
            && Objects.equal(pattern.flags(), that.pattern.flags());
      }
      return false;
    }
