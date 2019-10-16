	protected void ignoreNoise() {
		ignore(".*inv1.invoke.*");
		ignore(".*javax.servlet.http.HttpServlet.*");
		ignore(".*sun.reflect.*");
		ignore(".*java.lang.reflect.Method.*");
	}
