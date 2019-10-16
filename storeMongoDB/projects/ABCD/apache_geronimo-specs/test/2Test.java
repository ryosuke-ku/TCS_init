    public void testGetDeploymentManagerWithNullURI() {
        try {
            factoryManager.getDeploymentManager(null, null, null);
            fail("Expected an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch(DeploymentManagerCreationException e) {
            fail("Unexpected Exception: "+e.getMessage());
        }
    }
