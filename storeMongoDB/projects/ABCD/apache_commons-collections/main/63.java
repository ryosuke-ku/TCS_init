    public static <O> List<O> collate(final Iterable<? extends O> a, final Iterable<? extends O> b,
                                      final Comparator<? super O> c, final boolean includeDuplicates) {

        if (a == null || b == null) {
            throw new NullPointerException("The collections must not be null");
        }
        if (c == null) {
            throw new NullPointerException("The comparator must not be null");
        }

        // if both Iterables are a Collection, we can estimate the size
        final int totalSize = a instanceof Collection<?> && b instanceof Collection<?> ?
                Math.max(1, ((Collection<?>) a).size() + ((Collection<?>) b).size()) : 10;

        final Iterator<O> iterator = new CollatingIterator<O>(c, a.iterator(), b.iterator());
        if (includeDuplicates) {
            return IteratorUtils.toList(iterator, totalSize);
        } else {
            final ArrayList<O> mergedList = new ArrayList<O>(totalSize);

            O lastItem = null;
            while (iterator.hasNext()) {
                final O item = iterator.next();
                if (lastItem == null || !lastItem.equals(item)) {
                    mergedList.add(item);
                }
                lastItem = item;
            }

            mergedList.trimToSize();
            return mergedList;
        }
    }
