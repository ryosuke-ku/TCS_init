    public DeploymentManager getDeploymentManager(String uri, String username, String password) throws DeploymentManagerCreationException {
        if(uri == null) {
            throw new IllegalArgumentException("URI for DeploymentManager should not be null");
        }
        DeploymentManager manager = null;
        for(Iterator i = deploymentFactories.iterator(); i.hasNext();) {
            DeploymentFactory factory = (DeploymentFactory)i.next();
            if(factory.handlesURI(uri)) {
                manager = factory.getDeploymentManager(uri, username, password);
                if(manager != null) {
                    return manager;
                }
            }
        }
        throw new DeploymentManagerCreationException("Could not get DeploymentManager; No registered DeploymentFactory handles this URI");
    }
