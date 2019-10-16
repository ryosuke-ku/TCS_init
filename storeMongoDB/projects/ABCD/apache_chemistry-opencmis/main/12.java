        public int available() throws IOException {
            if (stream != null) {
                try {
                    return stream.available();
                } catch (IOException ioe) {
                    closeQuietly();
                    throw ioe;
                }
            } else {
                throw new IOException("Stream is already closed!");
            }
        }
