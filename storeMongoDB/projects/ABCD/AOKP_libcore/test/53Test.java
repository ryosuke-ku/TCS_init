    public void test_SSL_CTX_set_session_id_context() throws Exception {
        byte[] empty = new byte[0];
        try {
            NativeCrypto.SSL_CTX_set_session_id_context(NULL, empty);
            fail();
        } catch (NullPointerException expected) {
        }
        long c = NativeCrypto.SSL_CTX_new();
        try {
            NativeCrypto.SSL_CTX_set_session_id_context(c, null);
            fail();
        } catch (NullPointerException expected) {
        }
        NativeCrypto.SSL_CTX_set_session_id_context(c, empty);
        NativeCrypto.SSL_CTX_set_session_id_context(c, new byte[32]);
        try {
            NativeCrypto.SSL_CTX_set_session_id_context(c, new byte[33]);
        } catch (IllegalArgumentException expected) {
        }
        NativeCrypto.SSL_CTX_free(c);
    }
