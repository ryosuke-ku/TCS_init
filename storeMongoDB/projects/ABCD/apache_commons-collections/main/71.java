    public static <C> Collection<C> synchronizedCollection(final Collection<C> collection) {
        return SynchronizedCollection.synchronizedCollection(collection);
    }
