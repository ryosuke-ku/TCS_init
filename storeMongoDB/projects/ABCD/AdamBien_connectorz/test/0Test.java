    public void writeMultipleTimes() throws Exception {
        final String key = "multiWrite";

        this.cut.begin();
        final byte[] firstEntry = "hello ".getBytes();
        this.cut.write(key, firstEntry);

        final byte[] secondEntry = "world!".getBytes();
        this.cut.write(key, secondEntry);
        this.cut.commit();

        final byte[] actual = this.cut.fetch(key);
        assertThat(actual, is("hello world!".getBytes()));
    }    
