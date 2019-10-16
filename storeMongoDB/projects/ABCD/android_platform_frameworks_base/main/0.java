    static VerifierDeviceIdentity generate(Random rng) {
        long identity = rng.nextLong();
        return new VerifierDeviceIdentity(identity);
    }
