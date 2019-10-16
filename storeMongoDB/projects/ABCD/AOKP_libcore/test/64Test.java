    public void test_SSL_set_mode_and_clear_mode() throws Exception {
        try {
            NativeCrypto.SSL_set_mode(NULL, 0);
            fail();
        } catch (NullPointerException expected) {
        }

        long c = NativeCrypto.SSL_CTX_new();
        long s = NativeCrypto.SSL_new(c);
        // check SSL_MODE_HANDSHAKE_CUTTHROUGH off by default
        assertEquals(0, NativeCrypto.SSL_get_mode(s) & SSL_MODE_HANDSHAKE_CUTTHROUGH);
        // set SSL_MODE_HANDSHAKE_CUTTHROUGH on
        NativeCrypto.SSL_set_mode(s, SSL_MODE_HANDSHAKE_CUTTHROUGH);
        assertTrue((NativeCrypto.SSL_get_mode(s)
                & SSL_MODE_HANDSHAKE_CUTTHROUGH) != 0);
        // clear SSL_MODE_HANDSHAKE_CUTTHROUGH off
        NativeCrypto.SSL_clear_mode(s, SSL_MODE_HANDSHAKE_CUTTHROUGH);
        assertTrue((NativeCrypto.SSL_get_mode(s)
                    & SSL_MODE_HANDSHAKE_CUTTHROUGH) == 0);

        NativeCrypto.SSL_free(s);
        NativeCrypto.SSL_CTX_free(c);
    }
