    public static <T> Predicate<T> nullIsFalsePredicate(final Predicate<? super T> predicate){
        return NullIsFalsePredicate.nullIsFalsePredicate(predicate);
    }
