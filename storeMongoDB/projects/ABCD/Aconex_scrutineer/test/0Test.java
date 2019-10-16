    public void hasNextShouldReturnFalseForAnEmptyStream() throws IOException {
        when(idAndVersionDataReader.readNext()).thenReturn(null);
        IdAndVersionInputStreamIterator idAndVersionInputStreamIterator = new IdAndVersionInputStreamIterator(idAndVersionDataReader);
        assertThat(idAndVersionInputStreamIterator.hasNext(), is(false));
    }
