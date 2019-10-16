    public static <E> Collection<E> retainAll(final Iterable<E> collection,
                                              final Iterable<? extends E> retain,
                                              final Equator<? super E> equator) {

        final Transformer<E, EquatorWrapper<E>> transformer = new Transformer<E, EquatorWrapper<E>>() {
            @Override
            public EquatorWrapper<E> transform(E input) {
                return new EquatorWrapper<E>(equator, input);
            }
        };

        final Set<EquatorWrapper<E>> retainSet =
                collect(retain, transformer, new HashSet<EquatorWrapper<E>>());

        final List<E> list = new ArrayList<E>();
        for (final E element : collection) {
            if (retainSet.contains(new EquatorWrapper<E>(equator, element))) {
                list.add(element);
            }
        }
        return list;
    }
