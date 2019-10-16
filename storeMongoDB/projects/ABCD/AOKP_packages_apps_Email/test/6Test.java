    public void testGetHeaderParameter() {
        // if header is null, return null
        assertNull("null header check", MimeUtility.getHeaderParameter(null, "name"));
        
        // if name is null, return first param or full header
        // NOTE:  The docs are wrong - it returns the header (no params) in that case
//      assertEquals("null name first param per docs", "Param1Value", 
//              MimeUtility.getHeaderParameter(HEADER_MULTI_PARAMETER, null));
        assertEquals("null name first param per code", "header",
                MimeUtility.getHeaderParameter(HEADER_MULTI_PARAMETER, null));
        assertEquals("null name full header", HEADER_NO_PARAMETER,
                MimeUtility.getHeaderParameter(HEADER_NO_PARAMETER, null));
        
        // find name 
        assertEquals("get 1st param", "Param1Value",
                MimeUtility.getHeaderParameter(HEADER_MULTI_PARAMETER, "Param1Name"));
        assertEquals("get 2nd param", "Param2Value",
                MimeUtility.getHeaderParameter(HEADER_MULTI_PARAMETER, "Param2Name"));
        assertEquals("get missing param", null,
                MimeUtility.getHeaderParameter(HEADER_MULTI_PARAMETER, "Param3Name"));
        
        // case insensitivity
        assertEquals("get 2nd param all LC", "Param2Value",
                MimeUtility.getHeaderParameter(HEADER_MULTI_PARAMETER, "param2name"));
        assertEquals("get 2nd param all UC", "Param2Value",
                MimeUtility.getHeaderParameter(HEADER_MULTI_PARAMETER, "PARAM2NAME"));

        // quoting
        assertEquals("get 1st param", "Param1Value",
                MimeUtility.getHeaderParameter(HEADER_QUOTED_MULTI_PARAMETER, "Param1Name"));
        assertEquals("get 2nd param", "Param2Value",
                MimeUtility.getHeaderParameter(HEADER_QUOTED_MULTI_PARAMETER, "Param2Name"));

        // Don't fail when malformed
        assertEquals("malformed filename param", null,
                MimeUtility.getHeaderParameter(HEADER_MALFORMED_PARAMETER, "filename"));
    }
