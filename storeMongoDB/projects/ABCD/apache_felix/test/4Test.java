    public void testSpec2() throws Exception
    {
        URL url = getClass().getResource("/spec_repository.xml");
        RepositoryAdminImpl repoAdmin = createRepositoryAdmin();
        repoAdmin.addRepository(url);

        Resolver resolver = repoAdmin.resolver();

        // Create a Local Resource with an extender capability
        CapabilityImpl capability = new CapabilityImpl("osgi.extender");
        capability.addProperty("osgi.extender", "osgi.component");
        capability.addProperty("version", "Version", "1.3");

        org.apache.felix.bundlerepository.Capability[] capabilities = { capability };

        Resource resource = EasyMock.createMock(Resource.class);
        EasyMock.expect(resource.getSymbolicName()).andReturn("com.test.bundleA").anyTimes();
        EasyMock.expect(resource.getRequirements()).andReturn(null).anyTimes();
        EasyMock.expect(resource.getCapabilities()).andReturn(capabilities).anyTimes();
        EasyMock.expect(resource.getURI()).andReturn("http://test.com").anyTimes();
        EasyMock.expect(resource.isLocal()).andReturn(true).anyTimes();

        // Create a Local Resource with a service capability
        CapabilityImpl capability2 = new CapabilityImpl("service");
        capability2.addProperty("objectClass", "org.some.other.interface");
        capability2.addProperty("effective", "active");

        org.apache.felix.bundlerepository.Capability[] capabilities2 = { capability2 };

        Resource resource2 = EasyMock.createMock(Resource.class);
        EasyMock.expect(resource2.getSymbolicName()).andReturn("com.test.bundleB").anyTimes();
        EasyMock.expect(resource2.getRequirements()).andReturn(null).anyTimes();
        EasyMock.expect(resource2.getCapabilities()).andReturn(capabilities2).anyTimes();
        EasyMock.expect(resource2.getURI()).andReturn("http://test2.com").anyTimes();
        EasyMock.expect(resource2.isLocal()).andReturn(true).anyTimes();

        EasyMock.replay(resource, resource2);

        resolver.add(resource);
        resolver.add(resource2);

        // Set the requirements to get the bundle
        RequirementImpl requirement = new RequirementImpl("foo");
        requirement.setFilter("(bar=bread)");

        Requirement[] requirements = { requirement };

        Resource[] discoverResources = repoAdmin.discoverResources(requirements);
        assertNotNull(discoverResources);
        assertEquals(1, discoverResources.length);

        resolver.add(discoverResources[0]);
        assertTrue("Resolver could not resolve", resolver.resolve());
    }
