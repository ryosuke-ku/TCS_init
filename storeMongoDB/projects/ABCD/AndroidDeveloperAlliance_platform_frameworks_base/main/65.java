        public int getPort() {
            return port == NOT_CALCULATED
                    ? port = parsePort()
                    : port;
        }
