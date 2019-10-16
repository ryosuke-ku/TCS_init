    public void deleteAndCommit() throws Exception{
        final String key = "hey";
        this.cut.begin();
        final byte[] content = "duke".getBytes();
        this.cut.write(key, content);
        byte[] actual = this.cut.fetch(key);
        assertThat(actual,is(content));
        this.cut.commit();
        this.cut.begin();
        this.cut.delete(key);
        this.cut.commit();
        actual = this.cut.fetch(key);
        assertNull(actual);
    }
