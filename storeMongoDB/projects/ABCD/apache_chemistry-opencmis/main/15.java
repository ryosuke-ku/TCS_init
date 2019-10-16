        public synchronized void mark(int readlimit) {
            if (stream != null) {
                stream.mark(readlimit);
            }
        }
