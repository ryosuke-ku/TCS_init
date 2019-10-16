    public void testAccessUnmarshalling()
    {
        LOGGER.info( "Get Kalumet test_auto environment access for the test group" );
        Access access = kalumetModel.getEnvironment( "test_auto" ).getAccess( "test" );
        assertEquals( "false", access.getProperty( "admin" ).getValue() );
        assertEquals( "true", access.getProperty( "update" ).getValue() );
        assertEquals( "true", access.getProperty( "jee_application_servers_change" ).getValue() );
        assertEquals( "true", access.getProperty( "jee_application_servers_update" ).getValue() );
        assertEquals( "true", access.getProperty( "jee_application_servers_control" ).getValue() );
        assertEquals( "true", access.getProperty( "jee_resources_change" ).getValue() );
        assertEquals( "true", access.getProperty( "jee_resources_update" ).getValue() );
        assertEquals( "true", access.getProperty( "jee_applications_change" ).getValue() );
        assertEquals( "true", access.getProperty( "jee_applications_update" ).getValue() );
        assertEquals( "true", access.getProperty( "softwares_change" ).getValue() );
        assertEquals( "true", access.getProperty( "softwares_update" ).getValue() );
        assertEquals( "true", access.getProperty( "release" ).getValue() );
        assertEquals( "true", access.getProperty( "shell" ).getValue() );
        assertEquals( "true", access.getProperty( "browser" ).getValue() );
        assertEquals( "true", access.getProperty( "homepage" ).getValue() );
    }
