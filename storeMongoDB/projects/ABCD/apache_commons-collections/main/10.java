    public static <I, O> Transformer<I, O> ifTransformer(final Predicate<? super I> predicate,
                                                         final Transformer<? super I, ? extends O> trueTransformer,
                                                         final Transformer<? super I, ? extends O> falseTransformer) {
        return IfTransformer.ifTransformer(predicate, trueTransformer, falseTransformer);
    }
