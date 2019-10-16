    public void test_EVP_SignInit() throws Exception {
        final long ctx = NativeCrypto.EVP_SignInit("RSA-SHA256");
        assertTrue(ctx != NULL);
        NativeCrypto.EVP_MD_CTX_destroy(ctx);

        try {
            NativeCrypto.EVP_SignInit("foobar");
            fail();
        } catch (RuntimeException expected) {
        }
    }
