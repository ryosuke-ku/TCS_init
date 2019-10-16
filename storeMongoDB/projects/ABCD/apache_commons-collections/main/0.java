    public static <E> SynchronizedBag<E> synchronizedBag(final Bag<E> bag) {
        return new SynchronizedBag<E>(bag);
    }
