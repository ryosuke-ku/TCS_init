    public static <T> Transformer<T, T> chainedTransformer(
            final Collection<? extends Transformer<? super T, ? extends T>> transformers) {
        return ChainedTransformer.chainedTransformer(transformers);
    }
