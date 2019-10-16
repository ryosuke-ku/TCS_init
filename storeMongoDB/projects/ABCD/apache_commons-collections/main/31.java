    public static <E> E forEachButLast(final Iterator<E> iterator, final Closure<? super E> closure) {
        if (closure == null) {
            throw new NullPointerException("Closure must not be null.");
        }
        if (iterator != null) {
            while (iterator.hasNext()) {
                final E element = iterator.next();
                if (iterator.hasNext()) {
                    closure.execute(element);
                } else {
                    return element;
                }
            }
        }
        return null;
    }
