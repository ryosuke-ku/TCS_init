    public static <E> Collection<E> transformingCollection(final Collection<E> collection,
            final Transformer<? super E, ? extends E> transformer) {
        return TransformedCollection.transformingCollection(collection, transformer);
    }
