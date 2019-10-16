    public Collection<V> values() {
        return new AbstractCollection<V>() {

            @Override
            public Iterator<V> iterator() {
                return new ValueIterator();
            }

            @Override
            public int size() {
                return count;
            }
        };
    }
