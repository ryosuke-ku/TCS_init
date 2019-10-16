    public void testReadSingle() throws Exception {
        BoundedInputStream bounded;
        final byte[] helloWorld = "Hello World".getBytes();
        final byte[] hello      = "Hello".getBytes();

        // limit = length
        bounded = new BoundedInputStream(new ByteArrayInputStream(helloWorld), helloWorld.length);
        for (int i = 0; i < helloWorld.length; i++) {
            assertEquals("limit = length byte[" + i + "]", helloWorld[i], bounded.read());
        }
        assertEquals("limit = length end", -1, bounded.read());

        // limit > length
        bounded = new BoundedInputStream(new ByteArrayInputStream(helloWorld), helloWorld.length + 1);
        for (int i = 0; i < helloWorld.length; i++) {
            assertEquals("limit > length byte[" + i + "]", helloWorld[i], bounded.read());
        }
        assertEquals("limit > length end", -1, bounded.read());

        // limit < length
        bounded = new BoundedInputStream(new ByteArrayInputStream(helloWorld), hello.length);
        for (int i = 0; i < hello.length; i++) {
            assertEquals("limit < length byte[" + i + "]", hello[i], bounded.read());
        }
        assertEquals("limit < length end", -1, bounded.read());
    }
