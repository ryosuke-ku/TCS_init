    public void resolveGlobalErrorPath() {
        final ErrorViewResolver evs = new ErrorViewResolver(new JspViewResolver());
        final String resolvedPath = evs.resolveViewPathFor(ErrorRoute.DEFAULT.getRoute());
        assertThat(resolvedPath).isEqualTo("/ErrorFilter");
    }
