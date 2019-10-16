    public void testVerifierDeviceIdentity_Equals_Failure() {
        VerifierDeviceIdentity id1 = new VerifierDeviceIdentity(TEST_1);
        VerifierDeviceIdentity id2 = new VerifierDeviceIdentity(TEST_2);

        assertFalse("The two VerifierDeviceIdentity instances should be unique", id1.equals(id2));
    }
