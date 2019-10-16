    public static <E> List<E> toList(final Iterator<? extends E> iterator, final int estimatedSize) {
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        if (estimatedSize < 1) {
            throw new IllegalArgumentException("Estimated size must be greater than 0");
        }
        final List<E> list = new ArrayList<E>(estimatedSize);
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
