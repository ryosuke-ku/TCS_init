    public static <E> Collection<List<E>> permutations(final Collection<E> collection) {
        final PermutationIterator<E> it = new PermutationIterator<E>(collection);
        final Collection<List<E>> result = new LinkedList<List<E>>();
        while (it.hasNext()) {
            result.add(it.next());
        }
        return result;
    }
