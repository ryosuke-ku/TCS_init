    public void shouldThrowUnsupportedExceptionOnRemove() {
        IdAndVersionInputStreamIterator idAndVersionInputStreamIterator = new IdAndVersionInputStreamIterator(idAndVersionDataReader);
        idAndVersionInputStreamIterator.remove();
    }
