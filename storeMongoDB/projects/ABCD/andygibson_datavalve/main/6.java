    public T remove(int index) {
        T obj = values.get(index);
        values.remove(index);
        keys.remove(index);
        return obj;
    }
