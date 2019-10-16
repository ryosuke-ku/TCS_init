    public static <T> Predicate<T> asPredicate(final Transformer<? super T, Boolean> transformer) {
        return TransformerPredicate.transformerPredicate(transformer);
    }
