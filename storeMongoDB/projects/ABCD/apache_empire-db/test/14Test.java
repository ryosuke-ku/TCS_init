    public void testIterator()
    {
        Options options = new Options();
        options.add(Integer.valueOf(1), "txt", false);
        options.add(Integer.valueOf(2), "txt2", false);
        Iterator<OptionEntry> it = options.iterator();
        it.next();
        assertEquals("txt2", it.next().getText());
        it.remove();
        assertEquals(1, options.size());
    }
