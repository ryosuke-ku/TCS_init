    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        fetchList();
        for (int i = values.size() - 1; i >= 0; i--) {
            T object = values.get(i);
            if (!c.contains(object)) {
                remove(i);
                modified = true;
            }
        }
        return modified;
    }
