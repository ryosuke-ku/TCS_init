    public void maxSize() {
        final Set<String> set = new HashSet<String>();
        set.add("1");
        set.add("2");
        set.add("3");
        try {
            CollectionUtils.maxSize(null);
            fail();
        } catch (final NullPointerException ex) {
        }
        assertEquals(-1, CollectionUtils.maxSize(set));

        final Queue<String> buf = new CircularFifoQueue<String>(set);
        assertEquals(3, CollectionUtils.maxSize(buf));
        buf.remove("2");
        assertEquals(3, CollectionUtils.maxSize(buf));
        buf.add("2");
        assertEquals(3, CollectionUtils.maxSize(buf));
    }
