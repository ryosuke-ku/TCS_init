    public void testGetMacroIpAddress() throws PermErrorException, NoneException {
        SPFSession d = new SPFSession("mailfrom@fromdomain.com","helodomain.com","2001:DB8::CB01");
        assertEquals("2.0.0.1.0.D.B.8.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.C.B.0.1",d.getMacroIpAddress());
    }
