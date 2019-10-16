    public void testSoftwareUpdatePlanDatabaseSqlScriptUnmarshaling()
    {
        LOGGER.info(
            "Get Kalumet environment test_auto test software db_test database update plan sqlscript_test SQL script." );
        SqlScript sqlScript =
            kalumetModel.getEnvironment( "test_auto" ).getSoftware( "test" ).getDatabase( "db_test" ).getSqlScript(
                "sqlscript_test" );
        assertEquals( "sqlscript_test", sqlScript.getName() );
        assertEquals( true, sqlScript.isActive() );
        assertEquals( false, sqlScript.isBlocker() );
        assertEquals( true, sqlScript.isForce() );
        assertEquals( "http://www.example.com/sqlscript", sqlScript.getUri() );
    }
