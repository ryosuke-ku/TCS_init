    public static <C> Collection<C> unmodifiableCollection(final Collection<? extends C> collection) {
        return UnmodifiableCollection.unmodifiableCollection(collection);
    }
