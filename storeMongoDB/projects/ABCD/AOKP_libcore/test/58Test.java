    public void test_SSL_use_certificate() throws Exception {
        try {
            NativeCrypto.SSL_use_certificate(NULL, null);
            fail();
        } catch (NullPointerException expected) {
        }

        long c = NativeCrypto.SSL_CTX_new();
        long s = NativeCrypto.SSL_new(c);

        try {
            NativeCrypto.SSL_use_certificate(s, null);
            fail();
        } catch (NullPointerException expected) {
        }

        NativeCrypto.SSL_use_certificate(s, getServerCertificates());

        NativeCrypto.SSL_free(s);
        NativeCrypto.SSL_CTX_free(c);
    }
