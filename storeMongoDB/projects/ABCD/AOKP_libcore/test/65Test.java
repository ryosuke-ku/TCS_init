    public void test_SSL_new() throws Exception {
        long c = NativeCrypto.SSL_CTX_new();
        long s = NativeCrypto.SSL_new(c);

        assertTrue(s != NULL);
        assertTrue((NativeCrypto.SSL_get_options(s) & 0x01000000L) != 0); // SSL_OP_NO_SSLv2
        assertTrue((NativeCrypto.SSL_get_options(s) & NativeCrypto.SSL_OP_NO_SSLv3) == 0);
        assertTrue((NativeCrypto.SSL_get_options(s) & NativeCrypto.SSL_OP_NO_TLSv1) == 0);
        assertTrue((NativeCrypto.SSL_get_options(s) & NativeCrypto.SSL_OP_NO_TLSv1_1) == 0);
        assertTrue((NativeCrypto.SSL_get_options(s) & NativeCrypto.SSL_OP_NO_TLSv1_2) == 0);

        long s2 = NativeCrypto.SSL_new(c);
        assertTrue(s != s2);
        NativeCrypto.SSL_free(s2);

        NativeCrypto.SSL_free(s);
        NativeCrypto.SSL_CTX_free(c);
    }
