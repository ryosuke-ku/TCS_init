  public void testIn_apply() {
    Collection<Integer> nums = Arrays.asList(1, 5);
    Predicate<Integer> isOneOrFive = Predicates.in(nums);

    assertTrue(isOneOrFive.apply(1));
    assertTrue(isOneOrFive.apply(5));
    assertFalse(isOneOrFive.apply(3));
    assertFalse(isOneOrFive.apply(null));
  }
