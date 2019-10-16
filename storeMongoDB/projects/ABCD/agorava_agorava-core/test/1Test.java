    public void shouldReturnSignatureMethodString() {
        String expected = "RSA-SHA1";
        assertEquals(expected, service.getSignatureMethod());
    }
