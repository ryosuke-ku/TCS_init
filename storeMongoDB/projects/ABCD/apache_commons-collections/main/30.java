    public static <E> void forEach(final Iterator<E> iterator, final Closure<? super E> closure) {
        if (closure == null) {
            throw new NullPointerException("Closure must not be null");
        }

        if (iterator != null) {
            while (iterator.hasNext()) {
                final E element = iterator.next();
                closure.execute(element);
            }
        }
    }
