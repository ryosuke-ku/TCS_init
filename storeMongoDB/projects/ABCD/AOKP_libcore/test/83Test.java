    public void test_SSL_set_verify() throws Exception {
        try {
            NativeCrypto.SSL_set_verify(NULL, 0);
            fail();
        } catch (NullPointerException expected) {
        }

        long c = NativeCrypto.SSL_CTX_new();
        long s = NativeCrypto.SSL_new(c);
        NativeCrypto.SSL_set_verify(s, NativeCrypto.SSL_VERIFY_NONE);
        NativeCrypto.SSL_set_verify(s, NativeCrypto.SSL_VERIFY_PEER);
        NativeCrypto.SSL_set_verify(s, NativeCrypto.SSL_VERIFY_FAIL_IF_NO_PEER_CERT);
        NativeCrypto.SSL_set_verify(s, (NativeCrypto.SSL_VERIFY_PEER
                                        | NativeCrypto.SSL_VERIFY_FAIL_IF_NO_PEER_CERT));
        NativeCrypto.SSL_free(s);
        NativeCrypto.SSL_CTX_free(c);
    }
