    public static <O, R extends Collection<? super O>> R select(final Iterable<? extends O> inputCollection,
            final Predicate<? super O> predicate, R outputCollection, R rejectedCollection) {

        if (inputCollection != null && predicate != null) {
            for (final O element : inputCollection) {
                if (predicate.evaluate(element)) {
                    outputCollection.add(element);
                } else {
                    rejectedCollection.add(element);
                }
            }
        }
        return outputCollection;
    }
