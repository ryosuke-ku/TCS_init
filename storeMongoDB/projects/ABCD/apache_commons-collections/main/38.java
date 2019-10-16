    public static <O, R extends Collection<? super O>> R selectRejected(final Iterable<? extends O> inputCollection,
            final Predicate<? super O> predicate, final R outputCollection) {

        if (inputCollection != null && predicate != null) {
            for (final O item : inputCollection) {
                if (!predicate.evaluate(item)) {
                    outputCollection.add(item);
                }
            }
        }
        return outputCollection;
    }
