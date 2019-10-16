    public void testLoadValidMavenProxyConfiguration()
        throws IOException, InvalidConfigurationException
    {
        File confFile = ArchivaConfigurationTest.getTestFile( "src/test/conf/maven-proxy-complete.conf" );

        Configuration configuration = new Configuration();
        NetworkProxyConfiguration proxy = new NetworkProxyConfiguration();
        proxy.setHost( "original-host" );
        configuration.addNetworkProxy( proxy ); // overwritten

        loader.load( Files.newInputStream(confFile.toPath()), configuration );

        Map<String, ManagedRepositoryConfiguration> repositoryIdMap = configuration.getManagedRepositoriesAsMap();
        assertEquals( "Count repositories", 1, repositoryIdMap.size() );
        assertRepositoryExists( "maven-proxy", "target", repositoryIdMap.get( "maven-proxy" ) );

        Map<String, RemoteRepositoryConfiguration> remoteRepositoryMap = configuration.getRemoteRepositoriesAsMap();
        assertEquals( "Count repositories", 4, remoteRepositoryMap.size() );
        assertRepositoryExists( "local-repo", "file://target", remoteRepositoryMap.get( "local-repo" ) );
        assertRepositoryExists( "www-ibiblio-org", "http://www.ibiblio.org/maven2",
                                remoteRepositoryMap.get( "www-ibiblio-org" ) );
        assertRepositoryExists( "dist-codehaus-org", "http://dist.codehaus.org",
                                remoteRepositoryMap.get( "dist-codehaus-org" ) );
        assertRepositoryExists( "private-example-com", "http://private.example.com/internal",
                                remoteRepositoryMap.get( "private-example-com" ) );
    }
