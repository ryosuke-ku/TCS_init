  public void testDeploymentContent002() throws Exception {
    DeploymentContentImpl dc = new DeploymentContentImpl("com.travel.reservation.business;deployed-version=2.0");
    assertEquals("2.0", dc.getAttribute("deployed-version"));
    VersionRange vi = dc.getVersion();
    assertTrue(vi.isExactVersion());
    assertEquals(new Version("2.0"), dc.getExactVersion());
    assertEquals("com.travel.reservation.business", dc.getContentName());
    assertEquals("{deployed-version=2.0}", dc.getNameValueMap().toString());
  }
