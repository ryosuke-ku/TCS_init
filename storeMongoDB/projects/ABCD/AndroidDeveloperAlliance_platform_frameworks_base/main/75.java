        public String getHost() {
            @SuppressWarnings("StringEquality")
            boolean cached = (host != NOT_CACHED);
            return cached ? host
                    : (host = parseHost());
        }
