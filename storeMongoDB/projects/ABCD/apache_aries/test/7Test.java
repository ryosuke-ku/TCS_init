  public void testCreateAndConversion() throws Exception {
	  	AriesApplication app = createApplication (CONVERSION_EBA);	    
	    ApplicationMetadata appMeta = app.getApplicationMetadata();	    
	    assertEquals (appMeta.getApplicationName(), "conversion.eba");	   
	    assertEquals (appMeta.getApplicationSymbolicName(), "conversion.eba");	    
	    assertEquals (appMeta.getApplicationVersion(), new Version("0.0"));	    
	    List<Content> appContent = appMeta.getApplicationContents();
	    assertEquals (2, appContent.size());
	    Content fbw = new ContentImpl("hello.world.jar;version=\"[1.1.0, 1.1.0]\"");
	    Content mbl = new ContentImpl("helloWorld.war;version=\"[0.0.0, 0.0.0]\"");
	    assertTrue (appContent.contains(fbw));
	    assertTrue (appContent.contains(mbl));
	    
	    DeploymentMetadata dm = app.getDeploymentMetadata();
	    List<DeploymentContent> dcList = dm.getApplicationDeploymentContents();

	    assertEquals (2, dcList.size());
	    DeploymentContent dc1 = new DeploymentContentImpl ("hello.world.jar;deployed-version=1.1.0");
	    DeploymentContent dc2 = new DeploymentContentImpl ("helloWorld.war;deployed-version=0.0.0");
	    DeploymentContent dc3 = new DeploymentContentImpl ("a.handy.persistence.library;deployed-version=1.1.0");
	    assertTrue (dcList.contains(dc1));
	    assertTrue (dcList.contains(dc2));
	    
	    dcList = dm.getApplicationProvisionBundles();
	    
	    assertEquals(1, dcList.size());
	    assertTrue (dcList.contains(dc3));
	    
	    assertEquals(2, app.getBundleInfo().size());
	    BundleInfo info;
	    info = findBundleInfo(app.getBundleInfo(), "hello.world.jar");
	    assertNotNull(info);
	    assertEquals("HelloWorldJar", info.getHeaders().get(Constants.BUNDLE_NAME));
	    
	    info = findBundleInfo(app.getBundleInfo(), "helloWorld.war");
        assertNotNull(info);
        assertEquals("helloWorld.war", info.getHeaders().get(Constants.BUNDLE_NAME));
        assertEquals("/test", info.getHeaders().get("Bundle-ContextPath"));
  }
