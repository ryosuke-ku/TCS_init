    public static <I, O> Transformer<I, O> invokerTransformer(final String methodName, final Class<?>[] paramTypes,
                                                              final Object[] args) {
        return InvokerTransformer.invokerTransformer(methodName, paramTypes, args);
    }
