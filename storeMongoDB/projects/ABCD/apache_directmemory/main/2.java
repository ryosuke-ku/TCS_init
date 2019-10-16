    public DirectMemoryResponse buildResponse( InputStream inputStream )
        throws DirectMemoryException
    {
        try
        {
            JsonParser jp = this.jsonFactory.createJsonParser( inputStream );
            DirectMemoryResponse rs = new DirectMemoryResponse();

            JsonToken jsonToken = jp.nextToken();

            while ( jsonToken != JsonToken.END_OBJECT && jsonToken != null)
            {
                String fieldName = jp.getCurrentName();
                if ( DirectMemoryConstants.FOUND_FIELD_NAME.equals( fieldName ) )
                {
                    rs.setFound( jp.getValueAsBoolean() );
                }
                if ( DirectMemoryConstants.KEY_FIELD_NAME.equals( fieldName ) )
                {
                    rs.setKey( jp.getText() );
                }
                if ( DirectMemoryConstants.CACHE_CONTENT_FIELD_NAME.equals( fieldName ) )
                {
                    // binaryValue need to go to nextToken
                    jp.nextToken();
                    rs.setCacheContent( jp.getBinaryValue() );
                }
                jsonToken = jp.nextToken();
            }


            return rs;
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
