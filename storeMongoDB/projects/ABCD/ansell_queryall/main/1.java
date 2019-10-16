    public static String getResponseContentType(final String acceptHeader, final String userAgent,
            final ContentTypeNegotiator negotiator, final String fallback)
    {
        if(QueryallContentNegotiator.DEBUG)
        {
            QueryallContentNegotiator.LOG.debug("QueryallContentNegotiator: acceptHeader=" + acceptHeader
                    + " userAgent=" + userAgent);
        }
        
        final MediaRangeSpec bestMatch = negotiator.getBestMatch(acceptHeader, userAgent);
        
        if(bestMatch == null)
        {
            if(QueryallContentNegotiator.TRACE)
            {
                QueryallContentNegotiator.LOG
                        .trace("QueryallContentNegotiator: bestMatch not found, returning fallback instead");
            }
            
            return fallback;
        }
        
        return bestMatch.getMediaType();
    }
