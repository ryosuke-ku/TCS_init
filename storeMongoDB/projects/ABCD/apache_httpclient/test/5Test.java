    public void testWeakList() {
        final WeakList<Object> list = new WeakList<Object>();
        list.add("hello");
        list.add(null);

        // null objects are seen as GC'd, so we only expect a size of 1
        assertEquals(1, list.size());

        final Iterator<Object> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("hello", it.next());
        assertFalse(it.hasNext());
        boolean thrown = false;
        try {
            it.next();
        } catch (NoSuchElementException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
