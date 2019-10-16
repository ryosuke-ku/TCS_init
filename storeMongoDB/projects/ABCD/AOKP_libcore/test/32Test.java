    public void test_EVP_get_digestbyname() throws Exception {
        assertTrue(NativeCrypto.EVP_get_digestbyname("sha256") != NULL);

        try {
            NativeCrypto.EVP_get_digestbyname(null);
            fail();
        } catch (NullPointerException expected) {
        }

        try {
            NativeCrypto.EVP_get_digestbyname("");
            NativeCrypto.EVP_get_digestbyname("foobar");
            fail();
        } catch (RuntimeException expected) {
        }
    }
