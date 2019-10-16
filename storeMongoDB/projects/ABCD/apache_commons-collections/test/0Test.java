    public Bag<T> makeObject() {
        return SynchronizedBag.synchronizedBag(new HashBag<T>());
    }
