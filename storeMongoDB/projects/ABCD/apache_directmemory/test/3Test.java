    public void storeExpiredObject()
        throws Exception
    {

        Serializer serializer = SerializerFactory.createNewSerializer();

        Wine bordeaux = new Wine( "Bordeaux", "very great wine" );

        DirectMemoryRequest directMemoryRequest =
            new DirectMemoryRequest().setKey( "bordeaux" ).setCacheContent(
                serializer.serialize( bordeaux ) ).setExpiresIn( 3 );

        String rq = writer.generateJsonRequest( directMemoryRequest );

        MockHttpServletRequest putRequest = new MockHttpServletRequest();

        putRequest.setContentType( MediaType.APPLICATION_JSON );

        putRequest.setServletPath( "cache" );

        putRequest.setPathInfo( "/bordeaux" );

        putRequest.setContent( rq.getBytes() );

        MockHttpServletResponse putResponse = new MockHttpServletResponse();

        directMemoryServlet.doPut( putRequest, putResponse );

        assertEquals( HttpServletResponse.SC_OK, putResponse.getStatus() );

        Thread.sleep( 10 );

        // now retrieve the content

        MockHttpServletRequest getRequest = new MockHttpServletRequest();

        getRequest.addHeader( "Accept", MediaType.APPLICATION_JSON );

        getRequest.setPathInfo( "/bordeaux" );

        MockHttpServletResponse getResponse = new MockHttpServletResponse();

        directMemoryServlet.doGet( getRequest, getResponse );

        assertEquals( HttpServletResponse.SC_NO_CONTENT, getResponse.getStatus() );


    }
