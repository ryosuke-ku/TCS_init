    public void testDefaultCryptVariant() {
        // If salt is null or completely omitted, a random "$6$" is used.
        assertTrue(Crypt.crypt("secret").startsWith("$6$"));
        assertTrue(Crypt.crypt("secret", null).startsWith("$6$"));
    }
