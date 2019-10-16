    private void ensureFactoryRegistered() {
        DeploymentFactory[] factories = factoryManager.getDeploymentFactories();
        for (int i = 0; i < factories.length; i++) {
            if (factories[i] == mockFactory) {
                return;
            }
        }
        factoryManager.registerDeploymentFactory(new MockDeploymentFactory("deployer"));
    }
