  public AriesApplication resolve(AriesApplication originalApp, ResolveConstraint... constraints) throws ResolverException {
    AriesApplicationImpl application = new AriesApplicationImpl(originalApp.getApplicationMetadata(), originalApp.getBundleInfo(), _localPlatform);
    Manifest deploymentManifest = deploymentManifestManager.generateDeploymentManifest(originalApp, constraints);
    try {
      application.setDeploymentMetadata(_deploymentMetadataFactory.createDeploymentMetadata(deploymentManifest));
    } catch (IOException ioe) {
      throw new ResolverException(ioe);
    }
    // Store a reference to any modified bundles
    if (originalApp instanceof AriesApplicationImpl) {
      // TODO: are we really passing streams around ?
      application.setModifiedBundles(((AriesApplicationImpl) originalApp).getModifiedBundles());
    }
    return application;
  } 
