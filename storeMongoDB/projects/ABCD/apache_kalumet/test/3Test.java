    public void testSoftwareUpdatePlanCommandUnmarshalling()
    {
        LOGGER.info( "Get Kalumet test_auto environment test software, command_test command" );
        Command command = kalumetModel.getEnvironment( "test_auto" ).getSoftware( "test" ).getCommand( "command_test" );
        assertEquals( "command_test", command.getName() );
        assertEquals( true, command.isActive() );
        assertEquals( false, command.isBlocker() );
        assertEquals( "ls /tmp", command.getCommand() );
        assertEquals( "test", command.getAgent() );
    }
