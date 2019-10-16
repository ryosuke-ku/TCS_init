    public static <T> Predicate<T> transformedPredicate(
            final Transformer<? super T, ? extends T> transformer, final Predicate<? super T> predicate) {
        return TransformedPredicate.transformedPredicate(transformer, predicate);
    }
