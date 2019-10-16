    public void testMap() throws IOException {
        SessionParameterMap map = new SessionParameterMap();

        // bindings
        map.setAtomPubBindingUrl("http://atomoub/url");
        assertEquals(BindingType.ATOMPUB.value(), map.get(SessionParameter.BINDING_TYPE));
        assertEquals("http://atomoub/url", map.get(SessionParameter.ATOMPUB_URL));

        map.setWebServicesBindingUrl("http://webservices/url");
        assertEquals(BindingType.WEBSERVICES.value(), map.get(SessionParameter.BINDING_TYPE));
        assertEquals("http://webservices/url", map.get(SessionParameter.WEBSERVICES_REPOSITORY_SERVICE));
        assertEquals("http://webservices/url", map.get(SessionParameter.WEBSERVICES_NAVIGATION_SERVICE));
        assertEquals("http://webservices/url", map.get(SessionParameter.WEBSERVICES_OBJECT_SERVICE));
        assertEquals("http://webservices/url", map.get(SessionParameter.WEBSERVICES_VERSIONING_SERVICE));
        assertEquals("http://webservices/url", map.get(SessionParameter.WEBSERVICES_DISCOVERY_SERVICE));
        assertEquals("http://webservices/url", map.get(SessionParameter.WEBSERVICES_MULTIFILING_SERVICE));
        assertEquals("http://webservices/url", map.get(SessionParameter.WEBSERVICES_RELATIONSHIP_SERVICE));
        assertEquals("http://webservices/url", map.get(SessionParameter.WEBSERVICES_ACL_SERVICE));
        assertEquals("http://webservices/url", map.get(SessionParameter.WEBSERVICES_POLICY_SERVICE));

        map.setBrowserBindingUrl("http://browser/url");
        assertEquals(BindingType.BROWSER.value(), map.get(SessionParameter.BINDING_TYPE));
        assertEquals("http://browser/url", map.get(SessionParameter.BROWSER_URL));

        map.setLocalBindingClass(TestLocalSessionFactory.class);
        assertEquals(BindingType.LOCAL.value(), map.get(SessionParameter.BINDING_TYPE));
        assertEquals(TestLocalSessionFactory.class.getName(), map.get(SessionParameter.LOCAL_FACTORY));

        map.setAtomPubBindingUrl(null);
        assertNull(map.get(SessionParameter.BINDING_TYPE));
        assertNull(map.get(SessionParameter.ATOMPUB_URL));

        // user and password
        map.setUserAndPassword("user", "password");
        assertEquals("user", map.get(SessionParameter.USER));
        assertEquals("password", map.get(SessionParameter.PASSWORD));

        map.setUserAndPassword(null, "password");
        assertFalse(map.containsKey(SessionParameter.USER));
        assertFalse(map.containsKey(SessionParameter.PASSWORD));

        map.setProxyUserAndPassword("user", "password");
        assertEquals("user", map.get(SessionParameter.PROXY_USER));
        assertEquals("password", map.get(SessionParameter.PROXY_PASSWORD));

        // repository id
        map.setRepositoryId("repid");
        assertEquals("repid", map.get(SessionParameter.REPOSITORY_ID));
        map.setRepositoryId(null);
        assertFalse(map.containsKey(SessionParameter.REPOSITORY_ID));

        // authentication
        map.setAuthenticationProvider(TestAuthenticationProvider.class);
        assertEquals(TestAuthenticationProvider.class.getName(),
                map.get(SessionParameter.AUTHENTICATION_PROVIDER_CLASS));

        map.setAuthenticationProvider(null);
        assertNull(map.get(SessionParameter.AUTHENTICATION_PROVIDER_CLASS));

        map.setNtlmAuthentication("user", "password");
        assertEquals("user", map.get(SessionParameter.USER));
        assertEquals("password", map.get(SessionParameter.PASSWORD));
        assertEquals("false", map.get(SessionParameter.AUTH_HTTP_BASIC));
        assertEquals("false", map.get(SessionParameter.AUTH_SOAP_USERNAMETOKEN));
        assertNotNull(map.get(SessionParameter.AUTHENTICATION_PROVIDER_CLASS));

        map.setBasicAuthentication("user1", "password1");
        assertEquals("user1", map.get(SessionParameter.USER));
        assertEquals("password1", map.get(SessionParameter.PASSWORD));
        assertEquals("true", map.get(SessionParameter.AUTH_HTTP_BASIC));
        assertEquals("false", map.get(SessionParameter.AUTH_SOAP_USERNAMETOKEN));

        map.setUsernameTokenAuthentication("user2", "password2", true);
        assertEquals("user2", map.get(SessionParameter.USER));
        assertEquals("password2", map.get(SessionParameter.PASSWORD));
        assertEquals("true", map.get(SessionParameter.AUTH_HTTP_BASIC));
        assertEquals("true", map.get(SessionParameter.AUTH_SOAP_USERNAMETOKEN));

        map.setNoAuthentication();
        assertEquals("false", map.get(SessionParameter.AUTH_HTTP_BASIC));
        assertEquals("false", map.get(SessionParameter.AUTH_SOAP_USERNAMETOKEN));

        // locale
        map.setLocale(new Locale("de", "DE"));
        assertEquals("de", map.get(SessionParameter.LOCALE_ISO639_LANGUAGE));
        assertEquals("DE", map.get(SessionParameter.LOCALE_ISO3166_COUNTRY));

        // HTTP related
        map.setCookies(true);
        assertEquals("true", map.get(SessionParameter.COOKIES));
        map.setCookies(false);
        assertEquals("false", map.get(SessionParameter.COOKIES));

        map.setCompression(true);
        assertEquals("true", map.get(SessionParameter.COMPRESSION));

        map.setClientCompression(false);
        assertEquals("false", map.get(SessionParameter.CLIENT_COMPRESSION));

        map.setConnectionTimeout(12345);
        assertEquals("12345", map.get(SessionParameter.CONNECT_TIMEOUT));

        map.setReadTimeout(98765);
        assertEquals("98765", map.get(SessionParameter.READ_TIMEOUT));

        // header
        map.addHeader(null, "value");
        map.addHeader("header0", "value0");
        map.addHeader("header1", "value1");
        map.addHeader("header2", "value2");

        assertEquals("header0:value0", map.get(SessionParameter.HEADER + ".0"));
        assertEquals("header1:value1", map.get(SessionParameter.HEADER + ".1"));
        assertEquals("header2:value2", map.get(SessionParameter.HEADER + ".2"));

        // store and load
        File tmp = File.createTempFile("session", "parameters");
        try {
            map.store(tmp);

            SessionParameterMap map2 = new SessionParameterMap();
            map2.load(tmp);

            assertEquals(map.size(), map2.size());
            for (String key : map.keySet()) {
                assertEquals(map.get(key), map2.get(key));
            }
        } finally {
            tmp.delete();
        }

        // parse
        String parameters = map.toString();
        SessionParameterMap map2 = new SessionParameterMap();
        map2.parse(parameters);

        assertEquals(map.size(), map2.size());
        for (String key : map.keySet()) {
            assertEquals(map.get(key), map2.get(key));
        }
    }
