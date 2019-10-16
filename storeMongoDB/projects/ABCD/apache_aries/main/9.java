  public AriesApplicationContext update(AriesApplication app, DeploymentMetadata depMf) throws UpdateException {
    if (!(app instanceof AriesApplicationImpl)) throw new IllegalArgumentException("Argument is not AriesApplication created by this manager");
    
    if (!!!app.getApplicationMetadata().getApplicationSymbolicName().equals(depMf.getApplicationSymbolicName())
        || !!!app.getApplicationMetadata().getApplicationVersion().equals(depMf.getApplicationVersion())) {
      throw new IllegalArgumentException("The deployment metadata does not match the application.");
    }
    
    DeploymentMetadata oldMetadata = app.getDeploymentMetadata();
    
    AriesApplicationContext foundCtx = null;
    for (AriesApplicationContext ctx : _applicationContextManager.getApplicationContexts()) {
      if (ctx.getApplication().equals(app)) {
        foundCtx = ctx;
        break;
      }
    }
    
    ((AriesApplicationImpl) app).setDeploymentMetadata(depMf);
    
    if (foundCtx != null) {
      try {
        return _applicationContextManager.update(app, oldMetadata);
      } catch (UpdateException ue) {
        if (ue.hasRolledBack()) {
          ((AriesApplicationImpl) app).setDeploymentMetadata(oldMetadata);
        }
        
        throw ue;
      }
    } else {
      return null;
    }
  }
