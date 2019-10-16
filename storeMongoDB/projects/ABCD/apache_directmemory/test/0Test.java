    public void parseEmptyRequest()
        throws Exception
    {
        InputStream is = new ByteArrayInputStream( new byte[0] );
        DirectMemoryRequest dmRq = DirectMemoryParser.instance().buildRequest( is );
        assertNotNull( dmRq );
        assertNull( dmRq.getCacheContent() );

    }
