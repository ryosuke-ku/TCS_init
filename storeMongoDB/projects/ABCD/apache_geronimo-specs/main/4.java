    public void registerDeploymentFactory(DeploymentFactory factory) {
        if(factory == null) {
            throw new IllegalArgumentException("DeploymentFactory to register should not be null");
        }
        if(!deploymentFactories.contains(factory)) {
            deploymentFactories.add(factory);
        }
    }
