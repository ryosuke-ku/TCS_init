    public void testVerifierDeviceIdentity_ToString_MinValue() {
        VerifierDeviceIdentity id1 = new VerifierDeviceIdentity(TEST_MINVALUE);

        assertEquals("The identity should encode correctly to the expected Base 32 string",
                TEST_MINVALUE_ENCODED, id1.toString());
    }
