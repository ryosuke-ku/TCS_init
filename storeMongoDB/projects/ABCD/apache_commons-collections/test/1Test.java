    public void testDecorate() {
        final SortedBag<T> bag = decorateBag(new TreeBag<T>(), stringPredicate());
        ((PredicatedSortedBag<T>) bag).decorated();
        try {
            decorateBag(new TreeBag<T>(), null);
            fail("Expecting NullPointerException for null predicate");
        } catch (final NullPointerException e) {}
        try {
            decorateBag(nullBag, stringPredicate());
            fail("Expecting NullPointerException for null bag");
        } catch (final NullPointerException e) {}
    }
