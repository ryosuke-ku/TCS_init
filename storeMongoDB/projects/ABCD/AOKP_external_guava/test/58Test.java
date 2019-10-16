  public void testOr_equalityIterable() {
    new EqualsTester()
        .addEqualityGroup(
            Predicates.or(Arrays.asList(FALSE, NEVER_REACHED)),
            Predicates.or(Arrays.asList(FALSE, NEVER_REACHED)),
            Predicates.or(FALSE, NEVER_REACHED))
        .addEqualityGroup(Predicates.or(TRUE, NEVER_REACHED))
        .addEqualityGroup(Predicates.and(FALSE, NEVER_REACHED))
        .testEquals();
  }
