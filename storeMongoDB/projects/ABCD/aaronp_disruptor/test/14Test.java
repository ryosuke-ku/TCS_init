    public void shouldClaimAndGetWithTimeout() throws Exception
    {
        assertEquals(RingBuffer.INITIAL_CURSOR_VALUE, ringBuffer.getCursor());

        StubEntry expectedEntry = new StubEntry(2701);

        StubEntry oldEntry = producerBarrier.nextEntry();
        oldEntry.copy(expectedEntry);
        producerBarrier.commit(oldEntry);

        long sequence = consumerBarrier.waitFor(0, 5, TimeUnit.MILLISECONDS);
        assertEquals(0, sequence);

        StubEntry entry = ringBuffer.getEntry(sequence);
        assertEquals(expectedEntry, entry);

        assertEquals(0L, ringBuffer.getCursor());
    }
