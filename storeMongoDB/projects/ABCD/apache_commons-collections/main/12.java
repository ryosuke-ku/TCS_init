    public static <I, O> Transformer<I, O> switchTransformer(
            final Map<Predicate<I>, Transformer<I, O>> predicatesAndTransformers) {
        return SwitchTransformer.switchTransformer(predicatesAndTransformers);
    }
