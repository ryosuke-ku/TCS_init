    public void testGroupUnmarshalling()
    {
        LOGGER.info( "Get Kalumet test group" );
        Group group = kalumetModel.getSecurity().getGroup( "test" );
        assertEquals( "Test Group", group.getName() );
        assertEquals( "test", group.getUser( "test" ).getId() );
    }
