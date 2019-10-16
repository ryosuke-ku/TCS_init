    public static <T> Predicate<T> nullIsExceptionPredicate(final Predicate<? super T> predicate){
        return NullIsExceptionPredicate.nullIsExceptionPredicate(predicate);
    }
