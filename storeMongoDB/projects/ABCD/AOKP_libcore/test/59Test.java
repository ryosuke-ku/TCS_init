    public void test_SSL_check_private_key_key_then_cert() throws Exception {
        long c = NativeCrypto.SSL_CTX_new();
        long s = NativeCrypto.SSL_new(c);

        // first private, then certificate
        NativeCrypto.SSL_use_PrivateKey(s, getServerPrivateKey().getPkeyContext());

        try {
            NativeCrypto.SSL_check_private_key(s);
            fail();
        } catch (SSLException expected) {
        }

        NativeCrypto.SSL_use_certificate(s, getServerCertificates());
        NativeCrypto.SSL_check_private_key(s);

        NativeCrypto.SSL_free(s);
        NativeCrypto.SSL_CTX_free(c);
    }
