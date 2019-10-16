	protected void ignoreSpringSecurity() {
		ignore(".*org.springframework.security.context.HttpSessionContextIntegrationFilter.*");
		ignore(".*org.springframework.security.intercept.web.FilterSecurityInterceptor.*");
		ignore(".*org.springframework.security.providers.anonymous.AnonymousProcessingFilter.*");
		ignore(".*org.springframework.security.ui.AbstractProcessingFilter.*");
		ignore(".*org.springframework.security.ui.basicauth.BasicProcessingFilter.*");
		ignore(".*org.springframework.security.ui.ExceptionTranslationFilter.*");
		ignore(".*org.springframework.security.ui.logout.LogoutFilter.*");
		ignore(".*org.springframework.security.ui.rememberme.RememberMeProcessingFilter.*");
		ignore(".*org.springframework.security.ui.SessionFixationProtectionFilter.*");
		ignore(".*org.springframework.security.ui.SpringSecurityFilter.*");
		ignore(".*org.springframework.security.util.FilterChainProxy.*");
		ignore(".*org.springframework.security.wrapper.SecurityContextHolderAwareRequestFilter.*");
		ignore(".*org.springframework.web.filter.DelegatingFilterProxy.*");
	}
