	protected void setUp() throws Exception {
		super.setUp();
		
		final Properties p = new Properties();
        p.put("myds", "new://Resource?type=DataSource");
        p.put("myds.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("myds.JdbcUrl", "jdbc:hsqldb:mem:slidedb");
        
		this.ejbContainer = EJBContainer.createEJBContainer(p);
		Object oSlideService = ejbContainer.getContext().lookup("java:global/slides/SlideService");
		assertNotNull(oSlideService);
		this.slideService = (SlideService)oSlideService;
		this.slideService.initDb();
	}
