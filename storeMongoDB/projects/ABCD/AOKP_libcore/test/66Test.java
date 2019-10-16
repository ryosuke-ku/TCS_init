    public void test_SSL_set_options() throws Exception {
        try {
            NativeCrypto.SSL_set_options(NULL, 0);
            fail();
        } catch (NullPointerException expected) {
        }

        long c = NativeCrypto.SSL_CTX_new();
        long s = NativeCrypto.SSL_new(c);
        assertTrue((NativeCrypto.SSL_get_options(s) & NativeCrypto.SSL_OP_NO_SSLv3) == 0);
        NativeCrypto.SSL_set_options(s, NativeCrypto.SSL_OP_NO_SSLv3);
        assertTrue((NativeCrypto.SSL_get_options(s) & NativeCrypto.SSL_OP_NO_SSLv3) != 0);
        NativeCrypto.SSL_free(s);
        NativeCrypto.SSL_CTX_free(c);
    }
