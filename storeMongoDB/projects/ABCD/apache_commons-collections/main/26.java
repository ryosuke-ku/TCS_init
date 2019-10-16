    public static <T> Predicate<T> nullIsTruePredicate(final Predicate<? super T> predicate){
        return NullIsTruePredicate.nullIsTruePredicate(predicate);
    }
