    public void writeAndRollback() throws Exception{
        final String key = "hey";
        this.cut.begin();
        final byte[] content = "duke".getBytes();
        this.cut.write(key, content);
        byte[] actual = this.cut.fetch(key);
        assertThat(actual,is(content));
        this.cut.rollback();
        actual = this.cut.fetch(key);
        assertNull(actual);
    
    }
