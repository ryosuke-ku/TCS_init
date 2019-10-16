    public void testSettings()
        throws Exception
    {
        Settings settings = new Settings();
        settings.setBaseDirectory( new File( System.getProperty( "java.io.tmpdir" ) ) );
        assertNotNull( settings.getBaseDirectory() );

    }
