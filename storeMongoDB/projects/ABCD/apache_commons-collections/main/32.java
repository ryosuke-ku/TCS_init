    public static <E> E find(final Iterator<E> iterator, final Predicate<? super E> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null");
        }

        if (iterator != null) {
            while (iterator.hasNext()) {
                final E element = iterator.next();
                if (predicate.evaluate(element)) {
                    return element;
                }
            }
        }
        return null;
    }
