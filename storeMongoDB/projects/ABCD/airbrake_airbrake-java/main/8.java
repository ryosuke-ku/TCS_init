	protected void ignoreMortbayJetty() {
		ignore(".*org.mortbay.jetty.handler.ContextHandlerCollection.*");
		ignore(".*org.mortbay.jetty.handler.ContextHandler.*");
		ignore(".*org.mortbay.jetty.handler.HandlerCollection.*");
		ignore(".*org.mortbay.jetty.handler.HandlerWrapper.*");
		ignore(".*org.mortbay.jetty.HttpConnection.*");
		ignore(".*org.mortbay.jetty.HttpParser.*");
		ignore(".*org.mortbay.jetty.security.SecurityHandler.*");
		ignore(".*org.mortbay.jetty.Server.*");
		ignore(".*org.mortbay.jetty.servlet.ServletHandler.*");
		ignore(".*org.mortbay.jetty.servlet.ServletHolder.*");
		ignore(".*org.mortbay.jetty.servlet.SessionHandler.*");
		ignore(".*org.mortbay.jetty.webapp.WebAppContext.*");
		ignore(".*org.mortbay.io.nio.*");
		ignore(".*org.mortbay.thread.*");
	}
