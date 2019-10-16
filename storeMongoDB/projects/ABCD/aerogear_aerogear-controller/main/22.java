        public CorsConfiguration validRequestHeaders(final String... validHeaders) {
            validRequestHeaders.addAll(Arrays.asList(validHeaders));
            return build();
        }
