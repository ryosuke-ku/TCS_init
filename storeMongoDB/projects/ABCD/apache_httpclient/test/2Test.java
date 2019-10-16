    public void clearSupported() {
        final WeakList<Object> list = new WeakList<Object>();

        list.add("hello");
        assertEquals(1, list.size());

        list.clear();
        assertEquals(0, list.size());
    }
