    public void testKalumetUnmarshalling()
        throws Exception
    {
        Kalumet kalumet = Kalumet.digeste( "file:./target/kalumet.xml" );
        Software software = kalumet.getEnvironment( "test" ).getSoftware( "test" );
        assertEquals( "http://www.example.com/test?test=test&other=other", software.getUri() );
    }
