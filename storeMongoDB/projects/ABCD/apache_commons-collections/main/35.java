    public static <E> E get(final Iterator<E> iterator, final int index) {
        int i = index;
        CollectionUtils.checkIndexBounds(i);
        while (iterator.hasNext()) {
            i--;
            if (i == -1) {
                return iterator.next();
            }
            iterator.next();
        }
        throw new IndexOutOfBoundsException("Entry does not exist: " + i);
    }
