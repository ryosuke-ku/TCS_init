    public Set<K> keySet() {
        return new AbstractSet<K>() {

            @Override
            public void clear() {
                TreeMap.this.clear();
            }

            @SuppressWarnings("unchecked")
            @Override
            public boolean remove(Object o) {
                return TreeMap.this.remove((K) o) != null;
            }

            @Override
            public Iterator<K> iterator() {
                return new KeyIterator();
            }

            @Override
            public int size() {
                return count;
            }
        };

    }
