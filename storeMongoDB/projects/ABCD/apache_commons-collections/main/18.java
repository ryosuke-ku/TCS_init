    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer(
            final Class<?>[] paramTypes, final Object[] args) {
        return InstantiateTransformer.instantiateTransformer(paramTypes, args);
    }
