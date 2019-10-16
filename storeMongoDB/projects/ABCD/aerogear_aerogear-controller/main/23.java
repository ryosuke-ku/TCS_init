        public CorsConfiguration build() {
            validRequestHeaders.add("Origin");
            if (validRequestMethods.isEmpty()) {
                enableAllRequestMethods();
            }
            return new CorsConfig(this);
        }
