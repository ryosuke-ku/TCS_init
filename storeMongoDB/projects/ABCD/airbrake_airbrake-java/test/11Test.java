	public void testIgnoreSpringBacktrace() {
		final Iterable<String> backtrace = new Backtrace(backtrace()) {
			@Override
			protected void ignore() {
				ignoreSpringSecurity();
			}
		};

		assertThat(backtrace, not(hasItem("org.springframework.security.context.HttpSessionContextIntegrationFilter.doFilterHttp(HttpSessionContextIntegrationFilter.java:235)")));
		assertThat(backtrace, not(hasItem("org.springframework.security.intercept.web.FilterSecurityInterceptor.doFilter(FilterSecurityInterceptor.java:83)")));
		assertThat(backtrace, not(hasItem("org.springframework.security.providers.anonymous.AnonymousProcessingFilter.doFilterHttp(AnonymousProcessingFilter.java:105)")));
		assertThat(backtrace, not(hasItem("org.springframework.security.ui.AbstractProcessingFilter.doFilterHttp(AbstractProcessingFilter.java:271)")));
		assertThat(backtrace, not(hasItem("org.springframework.security.ui.basicauth.BasicProcessingFilter.doFilterHttp(BasicProcessingFilter.java:173)")));
		assertThat(backtrace, not(hasItem("org.springframework.security.ui.ExceptionTranslationFilter.doFilterHttp(ExceptionTranslationFilter.java:101)")));
		assertThat(backtrace, not(hasItem("org.springframework.security.ui.logout.LogoutFilter.doFilterHttp(LogoutFilter.java:89)")));
		assertThat(backtrace, not(hasItem("org.springframework.security.ui.rememberme.RememberMeProcessingFilter.doFilterHttp(RememberMeProcessingFilter.java:116)")));
		assertThat(backtrace, not(hasItem("org.springframework.security.ui.SessionFixationProtectionFilter.doFilterHttp(SessionFixationProtectionFilter.java:67)")));
		assertThat(backtrace, not(hasItem("org.springframework.security.ui.SpringSecurityFilter.doFilter(SpringSecurityFilter.java:53)")));
		assertThat(backtrace, not(hasItem("org.springframework.security.util.FilterChainProxy.doFilter(FilterChainProxy.java:174)")));
		assertThat(backtrace, not(hasItem("org.springframework.security.wrapper.SecurityContextHolderAwareRequestFilter.doFilterHttp(SecurityContextHolderAwareRequestFilter.java:91)")));
		assertThat(backtrace, not(hasItem("org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:167)")));
	}
