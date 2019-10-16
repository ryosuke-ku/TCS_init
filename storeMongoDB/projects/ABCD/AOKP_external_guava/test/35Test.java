  public void assertEqualHashCode(
      Predicate<? super Integer> expected, Predicate<? super Integer> actual) {
    assertEquals(actual.toString() + " should hash like " + expected.toString(),
        expected.hashCode(), actual.hashCode());
  }
