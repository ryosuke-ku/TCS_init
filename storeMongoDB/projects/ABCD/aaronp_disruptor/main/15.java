        public T claimEntry(final long sequence)
        {
            ensureConsumersAreInRange(sequence);

            AbstractEntry entry = entries[(int)sequence & ringModMask];
            entry.setSequence(sequence);

            return (T)entry;
        }
