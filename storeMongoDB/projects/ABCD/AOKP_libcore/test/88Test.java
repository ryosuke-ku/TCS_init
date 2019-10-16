    public void test_SSL_SESSION_free() throws Exception {
        try {
            NativeCrypto.SSL_SESSION_free(NULL);
            fail();
        } catch (NullPointerException expected) {
        }

        // additional positive testing elsewhere because handshake
        // uses use SSL_SESSION_free to cleanup in afterHandshake.
    }
