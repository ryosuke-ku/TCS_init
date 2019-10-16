    public void shouldExtractPathWithQueryAndFragment() throws URISyntaxException {
        assertThat(UrlHelper.getRequestPath(new URI("http://localhost:8902/status?ln=test%20value#help%20page")), is("/status?ln=test%20value#help%20page"));
    }
rser = new CookieParser();
        Cookie cookie = cookieParser.parseCookie("localhost", rawCookie);

        assertThat(cookie.getName(), is("USER"));
        assertThat(cookie.getValue(), is("bob"));
        assertThat(cookie.getExpires(), is(testDate));
    }
