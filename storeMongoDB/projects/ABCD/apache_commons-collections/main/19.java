    public static <I, O> Transformer<I, O> mapTransformer(final Map<? super I, ? extends O> map) {
        return MapTransformer.mapTransformer(map);
    }
