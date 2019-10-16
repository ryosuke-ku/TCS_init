    public void parseResponse()
        throws Exception
    {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream( "cache-rs.json" );
        assertNotNull( is );
        DirectMemoryResponse dmRs = DirectMemoryParser.instance().buildResponse( is );
        assertNotNull( dmRs );

        assertEquals( "foo", dmRs.getKey() );
        assertEquals( true, dmRs.isFound() );
        assertEquals( false, dmRs.isStored() );
        assertEquals( "foo bar", new String( dmRs.getCacheContent() ) );
    }
