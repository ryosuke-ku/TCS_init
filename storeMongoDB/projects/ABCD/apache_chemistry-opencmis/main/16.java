        public synchronized void reset() throws IOException {
            if (stream != null) {
                try {
                    stream.reset();
                } catch (IOException ioe) {
                    closeQuietly();
                    throw ioe;
                }
            } else {
                throw new IOException("Stream is already closed!");
            }
        }
