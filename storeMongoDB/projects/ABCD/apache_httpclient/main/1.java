    public boolean add(final T t) {
        return innerList.add(new WeakReference<T>(t));
    }
