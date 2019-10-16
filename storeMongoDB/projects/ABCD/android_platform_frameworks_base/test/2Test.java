    public void testVerifierDeviceIdentity_HashCode() {
        VerifierDeviceIdentity id1 = new VerifierDeviceIdentity(TEST_1);

        assertEquals("The VerifierDeviceIdentity should have the same hashcode as its identity",
                (int) TEST_1, id1.hashCode());
    }
