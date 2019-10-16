    public static <I, O> Transformer<I, O> asTransformer(final Factory<? extends O> factory) {
        return FactoryTransformer.factoryTransformer(factory);
    }
