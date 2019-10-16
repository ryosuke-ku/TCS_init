    public static DeploymentFactoryManager getInstance() {
        if(instance == null) {
            instance = new DeploymentFactoryManager();
        }
        return instance;
    }
