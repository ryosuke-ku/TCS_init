        public void closeQuietly() {
            if (stream != null) {
                try {
                    stream.close();
                } catch (final IOException ioe) {
                    // ignore
                } finally {
                    stream = null;
                }
            }
        }
