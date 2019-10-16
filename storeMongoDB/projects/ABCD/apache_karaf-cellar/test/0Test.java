    public void testDefaultInstanceWithSaxon() throws InterruptedException {
        System.setProperty("karaf.etc", "src/test/resources/etc");
        System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
        HazelcastServiceFactory factory = new HazelcastServiceFactory();
        factory.setConfigurationManager(new HazelcastConfigurationManager());
        factory.init();
        factory.getInstance();
        HazelcastInstance factoryInstance = factory.getInstance();

        HazelcastInstance defaultInstance = Hazelcast.newHazelcastInstance(null);

        Assert.assertEquals(true, factoryInstance.getCluster().getMembers().size() >= 2);

        factoryInstance.shutdown();
        defaultInstance.shutdown();
    }
