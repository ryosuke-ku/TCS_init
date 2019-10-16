    public static <E> int indexOf(final Iterator<E> iterator, final Predicate<? super E> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null");
        }

        if (iterator != null) {
            for(int index = 0; iterator.hasNext(); index++) {
                final E element = iterator.next();
                if (predicate.evaluate(element)) {
                    return index;
                }
            }
        }
        return -1;
    }
