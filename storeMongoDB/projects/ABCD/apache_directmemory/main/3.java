    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        // url format = /cache/key so get the key from path
        String path = req.getPathInfo();
        String servletPath = req.getServletPath();
        String key = retrieveKeyFromPath( path );

        if ( StringUtils.isEmpty( key ) )
        {
            resp.sendError( HttpServletResponse.SC_BAD_REQUEST, "key missing in path" );
            return;
        }

        String acceptContentType = req.getHeader( "Accept" );

        if ( StringUtils.isEmpty( acceptContentType ) )
        {
            resp.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            "you must specify Accept with Content-Type you want in the response" );
            return;
        }

        ContentTypeHandler contentTypeHandler = findGetCacheContentTypeHandler( req, resp );

        if ( contentTypeHandler == null )
        {
            String contentType = req.getContentType();
            log.error( "No content type handler for content type {}", acceptContentType );
            resp.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            "Content-Type: " + acceptContentType + " not supported" );
            return;
        }

        byte[] bytes = cacheService.retrieveByteArray( key );

        log.debug( "return content size {} for key {}", ( bytes == null ? "null" : bytes.length ), key );

        if ( bytes == null || bytes.length == 0 )
        {
            resp.sendError( HttpServletResponse.SC_NO_CONTENT, "No content for key: " + key );
            return;
        }

        try
        {
            byte[] respBytes =
                contentTypeHandler.handleGet( new DirectMemoryRequest().setKey( key ), bytes, resp, req );
            resp.getOutputStream().write( respBytes );
        }
        catch ( DirectMemoryException e )
        {
            resp.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage() );
        }


    }
