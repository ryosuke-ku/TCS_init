    public void skipLastSlashWithoutExistingSlash(){
        String expected = "hey";
        String origin = "hey";
        String actual = this.cut.skipLastSlash(origin);
        assertThat(actual,is(expected));
    }
