    public void testVerifierDeviceIdentity_Generate_Random() {
        VerifierDeviceIdentity id1 = new VerifierDeviceIdentity(TEST_1);

        MockRandom random = new MockRandom();
        random.setNextLong(TEST_1);
        VerifierDeviceIdentity id2 = VerifierDeviceIdentity.generate(random);

        assertEquals("Identity should end up being same when coming from RNG", id1, id2);
    }
