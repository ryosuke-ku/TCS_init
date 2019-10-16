    public static <T> T median(final Comparator<T> comparator, final T... items) {
        Validate.notEmpty(items, "null/empty items");
        Validate.noNullElements(items);
        Validate.notNull(comparator, "null comparator");
        final TreeSet<T> sort = new TreeSet<>(comparator);
        Collections.addAll(sort, items);
        @SuppressWarnings("unchecked") //we know all items added were T instances
        final
        T result = (T) sort.toArray()[(sort.size() - 1) / 2];
        return result;
    }
