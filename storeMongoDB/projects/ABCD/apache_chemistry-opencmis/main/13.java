        public void close() throws IOException {
            if (stream != null) {
                try {
                    stream.close();
                } catch (final IOException ioe) {
                    throw ioe;
                } finally {
                    stream = null;
                }
            }
        }
