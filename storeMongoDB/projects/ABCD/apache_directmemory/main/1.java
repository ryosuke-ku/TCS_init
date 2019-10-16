    public DirectMemoryRequest buildRequest( InputStream inputStream )
        throws DirectMemoryException
    {
        try
        {
            JsonParser jp = this.jsonFactory.createJsonParser( inputStream );
            DirectMemoryRequest rq = new DirectMemoryRequest();
            JsonToken jsonToken = jp.nextToken();
            while ( jsonToken != JsonToken.END_OBJECT && jsonToken != null )
            {
                String fieldName = jp.getCurrentName();
                if ( DirectMemoryConstants.KEY_FIELD_NAME.equals( fieldName ) )
                {
                    rq.setKey( jp.getText() );
                }
                if ( DirectMemoryConstants.PUT_FIELD_NAME.equals( fieldName ) )
                {
                    rq.setUpdate( jp.getValueAsBoolean() );
                }
                if ( DirectMemoryConstants.EXPIRES_IN_FIELD_NAME.equals( fieldName ) )
                {
                    rq.setExpiresIn( jp.getValueAsInt() );
                }
                if ( DirectMemoryConstants.CACHE_CONTENT_FIELD_NAME.equals( fieldName ) )
                {
                    // binaryValue need to go to nextToken
                    jp.nextToken();
                    rq.setCacheContent( jp.getBinaryValue() );
                }
                jsonToken = jp.nextToken();
            }

            jp.close();

            return rq;
        }
        catch ( JsonParseException e )
        {
            throw new DirectMemoryException( e.getMessage(), e );

        }
        catch ( IOException e )
        {
            throw new DirectMemoryException( e.getMessage(), e );
        }
    }
