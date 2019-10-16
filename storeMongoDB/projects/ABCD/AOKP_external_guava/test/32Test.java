  public void testCompose() {
    Function<String, String> trim = TrimStringFunction.INSTANCE;
    Predicate<String> equalsFoo = Predicates.equalTo("Foo");
    Predicate<String> equalsBar = Predicates.equalTo("Bar");
    Predicate<String> trimEqualsFoo = Predicates.compose(equalsFoo, trim);
    Function<String, String> identity = Functions.identity();

    assertTrue(trimEqualsFoo.apply("Foo"));
    assertTrue(trimEqualsFoo.apply("   Foo   "));
    assertFalse(trimEqualsFoo.apply("Foo-b-que"));

    new EqualsTester()
        .addEqualityGroup(trimEqualsFoo, Predicates.compose(equalsFoo, trim))
        .addEqualityGroup(equalsFoo)
        .addEqualityGroup(trim)
        .addEqualityGroup(Predicates.compose(equalsFoo, identity))
        .addEqualityGroup(Predicates.compose(equalsBar, trim))
        .testEquals();
  }
