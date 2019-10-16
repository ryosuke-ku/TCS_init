    public void testGetQueryParameterEmptyKey4() {
      Uri uri = Uri.parse("http://www.google.com?a=b&");
      assertEquals("", uri.getQueryParameter(""));
    }
