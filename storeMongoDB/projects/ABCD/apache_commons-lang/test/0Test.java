    public void testLANG805() {
        final long seed = System.currentTimeMillis();
        assertEquals("aaa", RandomStringUtils.random(3,0,0,false,false,new char[]{'a'},new Random(seed)));
    }
