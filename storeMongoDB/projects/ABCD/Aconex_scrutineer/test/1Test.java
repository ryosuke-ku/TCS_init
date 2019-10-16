    public void shouldGetTheNextItem() throws IOException {
        IdAndVersion idAndVersion = new StringIdAndVersion(ID, VERSION);
        when(idAndVersionDataReader.readNext()).thenReturn(idAndVersion);
        IdAndVersionInputStreamIterator idAndVersionInputStreamIterator = new IdAndVersionInputStreamIterator(idAndVersionDataReader);
        assertThat(idAndVersionInputStreamIterator.next(), is(idAndVersion));
    }
