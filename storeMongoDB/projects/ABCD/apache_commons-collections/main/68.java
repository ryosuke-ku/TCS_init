    public static <E> Collection<E> removeAll(final Iterable<E> collection,
                                              final Iterable<? extends E> remove,
                                              final Equator<? super E> equator) {

        final Transformer<E, EquatorWrapper<E>> transformer = new Transformer<E, EquatorWrapper<E>>() {
            @Override
            public EquatorWrapper<E> transform(E input) {
                return new EquatorWrapper<E>(equator, input);
            }
        };

        final Set<EquatorWrapper<E>> removeSet =
                collect(remove, transformer, new HashSet<EquatorWrapper<E>>());

        final List<E> list = new ArrayList<E>();
        for (final E element : collection) {
            if (!removeSet.contains(new EquatorWrapper<E>(equator, element))) {
                list.add(element);
            }
        }
        return list;
    }
