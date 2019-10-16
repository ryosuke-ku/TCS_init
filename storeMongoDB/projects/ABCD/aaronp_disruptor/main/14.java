        public void commit(final T entry)
        {
            long sequence = entry.getSequence();
            claimStrategy.setSequence(sequence);
            cursor = sequence;
            waitStrategy.signalAll();
        }
