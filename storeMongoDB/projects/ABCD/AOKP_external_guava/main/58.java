  private static <T> List<Predicate<? super T>> asList(
      Predicate<? super T> first, Predicate<? super T> second) {
    return Arrays.<Predicate<? super T>>asList(first, second);
  }
