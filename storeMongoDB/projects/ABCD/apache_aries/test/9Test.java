  public void testUpdateWithIncorrectDepMf() throws Exception
  {
    AriesApplication app = createApplication(TEST_EBA);

    DeploymentMetadata depMf = Skeleton.newMock(DeploymentMetadata.class);
    Skeleton.getSkeleton(depMf).setReturnValue(new MethodCall(DeploymentMetadata.class, "getApplicationSymbolicName"), "random.app");
    Skeleton.getSkeleton(depMf).setReturnValue(new MethodCall(DeploymentMetadata.class, "getApplicationVersion"), new Version("1.0.0"));
    
    AriesApplicationContextManager ctxMgr = Skeleton.newMock(AriesApplicationContextManager.class);
    _appMgr.setApplicationContextManager(ctxMgr);

    _appMgr.update(app, depMf);    
  }
