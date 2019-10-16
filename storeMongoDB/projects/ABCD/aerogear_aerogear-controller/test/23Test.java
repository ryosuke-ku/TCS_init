    public void enableAllRequestMethods() throws Exception {
        final CorsConfiguration config = CorsConfig.enableCorsSupport().anyOrigin().enableCookies().maxAge(10L)
                .enableAllRequestMethods().build();
        assertThat(config.getValidRequestMethods()).contains("GET", "PUT", "POST", "DELETE", "OPTIONS", "HEAD", "PATCH");
    }
