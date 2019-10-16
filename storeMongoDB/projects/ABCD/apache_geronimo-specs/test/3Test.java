    public void testDisconnectedGetDeploymentManagerWithNullURI() {
        try {
            factoryManager.getDisconnectedDeploymentManager(null);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch(DeploymentManagerCreationException e) {
            fail("Unexpected Exception: "+e.getMessage());
        }
    }
