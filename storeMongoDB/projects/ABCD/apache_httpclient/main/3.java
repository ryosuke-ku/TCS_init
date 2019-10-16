    public Iterator<T> iterator() {
        return new WeakIterator<T>(innerList.iterator());
    }
