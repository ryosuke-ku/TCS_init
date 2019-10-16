    @Test public void testUris() throws Exception 
        {
        final Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("fromSiteListener", "true");
        params.put("appVersion", String.valueOf(0.80));
        
        final String uri =
            UriUtils.newUrl(ShootConstants.SERVER_URL, params);
        
        //final String uri = "http://www.littleshoot.org/?fromSiteListener=true";
        //final Process p = UriUtils.openUri(uri);
        //assertNotNull(p);
        
        
        String file = "file:///Users/adamfisk";
        //final Process p = UriUtils.openUri(file);
        }
