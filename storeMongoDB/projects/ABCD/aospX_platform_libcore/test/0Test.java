    public void testReadDoesNotBlockUnnecessarily() throws IOException {
        PipedInputStream pin = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream(pin);
        pos.write("hello".getBytes("UTF-8"));

        InputStreamReader reader = new InputStreamReader(pin);
        char[] buffer = new char[1024];
        int count = reader.read(buffer);
        assertEquals(5, count);
    }
