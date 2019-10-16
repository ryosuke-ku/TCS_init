    public void testVerifierDeviceIdentity_Parse_Overflow() {
        try {
            VerifierDeviceIdentity.parse(TEST_OVERFLOW_ENCODED);
            fail("Parsing should fail when the value will overflow");
        } catch (IllegalArgumentException e) {
            // success
        }
    }
