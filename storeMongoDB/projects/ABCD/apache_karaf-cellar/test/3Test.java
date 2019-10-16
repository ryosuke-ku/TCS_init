    public void testDiscoveryFailure() {
        EtcdDiscoveryService discovery = new EtcdDiscoveryService();
        discovery.setEtcdServiceName("invalidServiceName");
        discovery.setEtcdEndpoint("http://127.0.0.1:2379");
        discovery.signIn();

        Set<String> members = discovery.discoverMembers();
        assertTrue(members.isEmpty());
    }
