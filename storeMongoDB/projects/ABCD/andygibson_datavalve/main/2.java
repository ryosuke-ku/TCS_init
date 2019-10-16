    public boolean addAll(int index, Collection<? extends T> c) {
        List<K> tempKeys = new ArrayList<K>();

        for (T item : c) {
            tempKeys.add(fetchKeyForValue(item));
        }
        keys.addAll(index, tempKeys);
        return values.addAll(index, c);
    }
