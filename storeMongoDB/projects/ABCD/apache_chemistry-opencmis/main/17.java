        public boolean markSupported() {
            if (stream != null) {
                return stream.markSupported();
            }

            return false;
        }
