        public int read(byte[] b, int off, int len) throws IOException {
            if (stream != null) {
                int l = -1;

                try {
                    l = stream.read(b, off, len);
                } catch (IOException ioe) {
                    closeQuietly();
                    throw ioe;
                }

                if (l == -1) {
                    close();
                }

                return l;
            } else {
                throw new IOException("Stream is already closed!");
            }
        }
