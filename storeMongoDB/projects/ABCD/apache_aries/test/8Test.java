  private AriesApplication createApplication (String fileName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, ManagementException, ResolverException {
    // This next block is a very long winded way of constructing a BundleInfoImpl
    // against the existing (BundleManifest bm, String location) constructor. If we 
    // find we need a String-based BundleInfoImpl constructor for other reasons, 
    // we could change to using it here. 
    Set<BundleInfo> nextResolverResult = new HashSet<BundleInfo>();
    String persistenceLibraryLocation = "../src/test/resources/bundles/repository/a.handy.persistence.library.jar";
    File persistenceLibrary = new File (persistenceLibraryLocation);
    BundleManifest mf = BundleManifest.fromBundle(persistenceLibrary);
    BundleInfo resolvedPersistenceLibrary = new SimpleBundleInfo(mf, persistenceLibraryLocation); 
    Field v = SimpleBundleInfo.class.getDeclaredField("_version");
    v.setAccessible(true);
    v.set(resolvedPersistenceLibrary, new Version("1.1.0"));
    nextResolverResult.add(resolvedPersistenceLibrary);
    _resolver.setNextResult(nextResolverResult);
    
    IDirectory testEba = FileSystem.getFSRoot(new File(fileName));    
    AriesApplication app = _appMgr.createApplication(testEba);
    app = _appMgr.resolve(app);
    return app;
  }
