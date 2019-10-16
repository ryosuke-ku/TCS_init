    public void constructor_GivenHttpUrl_SingleRule_InputStream() throws Exception {
        String path = "/profiles/export?format=pmd&language=java&name=Sonar%2520way";
        String completePath = path + "/DummyBasicMockRule";
        String hostpart = "http://localhost:" + wireMockRule.port();
        String basicRuleSet = IOUtils.toString(RuleSetReferenceId.class.getResourceAsStream("/rulesets/dummy/basic.xml"));

        stubFor(head(urlEqualTo(completePath)).willReturn(aResponse().withStatus(404)));
        stubFor(head(urlEqualTo(path)).willReturn(aResponse().withStatus(200).withHeader("Content-type", "text/xml")));
        stubFor(get(urlEqualTo(path)).willReturn(aResponse().withStatus(200).withHeader("Content-type", "text/xml").withBody(basicRuleSet)));


        RuleSetReferenceId ruleSetReferenceId = new RuleSetReferenceId("  " + hostpart + completePath + "  ");
        assertRuleSetReferenceId(true, hostpart + path, false, "DummyBasicMockRule", hostpart + completePath, ruleSetReferenceId);

        InputStream inputStream = ruleSetReferenceId.getInputStream(RuleSetReferenceIdTest.class.getClassLoader());
        String loaded = IOUtils.toString(inputStream, "UTF-8");
        assertEquals(basicRuleSet, loaded);

        verify(1, headRequestedFor(urlEqualTo(completePath)));
        verify(1, headRequestedFor(urlEqualTo(path)));
        verify(1, getRequestedFor(urlEqualTo(path)));
        verify(0, getRequestedFor(urlEqualTo(completePath)));
        assertEquals(2, findAll(headRequestedFor(urlMatching(".*"))).size());
        assertEquals(1, findAll(getRequestedFor(urlMatching(".*"))).size());
    }
