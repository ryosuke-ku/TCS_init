    public DeploymentManager getDisconnectedDeploymentManager(String uri) throws DeploymentManagerCreationException {
        if(uri == null) {
            throw new IllegalArgumentException("URI for DeploymentManager should not be null");
        }
        DeploymentManager manager = null;
        for(Iterator i = deploymentFactories.iterator(); i.hasNext();) {
            DeploymentFactory factory = (DeploymentFactory)i.next();
            if(factory.handlesURI(uri)) {
                manager = factory.getDisconnectedDeploymentManager(uri);
                if(manager != null) {
                    return manager;
                }
            }
        }
        throw new DeploymentManagerCreationException("Could not get DeploymentManager; No registered DeploymentFactory handles this URI");
    }
