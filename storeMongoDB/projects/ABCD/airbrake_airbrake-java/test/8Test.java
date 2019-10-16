	public void testIgnoreMortbayJettyBacktrace() {
		final Iterable<String> backtrace = new Backtrace(backtrace()) {
			@Override
			protected void ignore() {
				ignoreMortbayJetty();
			}
		};

		assertThat(backtrace, not(hasItem("org.mortbay.jetty.handler.ContextHandlerCollection.handle(ContextHandlerCollection.java:206)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.handler.ContextHandler.handle(ContextHandler.java:729)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.handler.HandlerCollection.handle(HandlerCollection.java:114)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.handler.HandlerWrapper.handle(HandlerWrapper.java:152)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.HttpConnection.handle(HttpConnection.java:380)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.HttpConnection.handleRequest(HttpConnection.java:505)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.HttpConnection$RequestHandler.content(HttpConnection.java:843)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.HttpParser.parseAvailable(HttpParser.java:211)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.HttpParser.parseNext(HttpParser.java:647)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.security.SecurityHandler.handle(SecurityHandler.java:216)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.Server.handle(Server.java:324)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.servlet.ServletHandler$CachedChain.doFilter(ServletHandler.java:1,088)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.servlet.ServletHandler.handle(ServletHandler.java:360)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.servlet.ServletHolder.handle(ServletHolder.java:487)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.servlet.SessionHandler.handle(SessionHandler.java:181)")));
		assertThat(backtrace, not(hasItem("org.mortbay.jetty.webapp.WebAppContext.handle(WebAppContext.java:405)")));
		assertThat(backtrace, not(hasItem("org.mortbay.io.nio.SelectChannelEndPoint.run(SelectChannelEndPoint.java:395)")));
		assertThat(backtrace, not(hasItem("org.mortbay.thread.QueuedThreadPool$PoolThread.run(QueuedThreadPool.java:488)")));
	}
