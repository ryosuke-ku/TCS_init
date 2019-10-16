	public void test01() throws Exception
	{
		InputStream in = null;
		if (System.getProperty("MRPTestFile") != null)
		{
			in = new FileInputStream(System.getProperty("MRPTestFile"));
		}
		if (in == null)
		{
			in = getClass().getClassLoader().getResourceAsStream("test/functional/mrp/MRPTests.csv");
		}
		if (System.getProperty("UseArhipacURL") != null || in == null)
		{
			String url = "http://spreadsheets.google.com/pub?key=p_F3GDtQxWTArVGQnNvicVw&output=csv&gid=0"; 
			in = new URL(url).openStream();
		}
		if (in == null)
		{
			throw new AdempiereException("No input test file found");
		}
		//
		CSVFactory factory = new CSVFactory();
		Collection<TestableMRP> tests = factory.read(in);
		//
		for (TestableMRP test : tests)
		{
			runTest(test);
			rollback();
		}
	}
