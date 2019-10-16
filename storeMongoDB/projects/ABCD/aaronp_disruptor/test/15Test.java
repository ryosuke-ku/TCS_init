    public void shouldSetAtSpecificSequence() throws Exception
    {
        long expectedSequence = 5;
        ForceFillProducerBarrier<StubEntry> forceFillProducerBarrier = ringBuffer.createForceFillProducerBarrier(new NoOpConsumer(ringBuffer));

        StubEntry expectedEntry = forceFillProducerBarrier.claimEntry(expectedSequence);
        expectedEntry.setValue((int) expectedSequence);
        forceFillProducerBarrier.commit(expectedEntry);

        long sequence = consumerBarrier.waitFor(expectedSequence);
        assertEquals(expectedSequence, sequence);

        StubEntry entry = ringBuffer.getEntry(sequence);
        assertEquals(expectedEntry, entry);

        assertEquals(expectedSequence, ringBuffer.getCursor());
    }
