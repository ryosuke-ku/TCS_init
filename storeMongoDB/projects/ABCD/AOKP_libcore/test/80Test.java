    public void test_SSL_write() throws Exception {
        try {
            NativeCrypto.SSL_write(NULL, null, null, null, 0, 0, 0);
            fail();
        } catch (NullPointerException expected) {
        }

        // null FileDescriptor
        {
            long c = NativeCrypto.SSL_CTX_new();
            long s = NativeCrypto.SSL_new(c);
            try {
                NativeCrypto.SSL_write(s, null, DUMMY_CB, null, 0, 1, 0);
                fail();
            } catch (NullPointerException expected) {
            }
            NativeCrypto.SSL_free(s);
            NativeCrypto.SSL_CTX_free(c);
        }

        // null SSLHandshakeCallbacks
        {
            long c = NativeCrypto.SSL_CTX_new();
            long s = NativeCrypto.SSL_new(c);
            try {
                NativeCrypto.SSL_write(s, INVALID_FD, null, null, 0, 1, 0);
                fail();
            } catch (NullPointerException expected) {
            }
            NativeCrypto.SSL_free(s);
            NativeCrypto.SSL_CTX_free(c);
        }

        // null byte array
        {
            long c = NativeCrypto.SSL_CTX_new();
            long s = NativeCrypto.SSL_new(c);
            try {
                NativeCrypto.SSL_write(s, INVALID_FD, DUMMY_CB, null, 0, 1, 0);
                fail();
            } catch (NullPointerException expected) {
            }
            NativeCrypto.SSL_free(s);
            NativeCrypto.SSL_CTX_free(c);
        }

        // handshaking not yet performed
        {
            long c = NativeCrypto.SSL_CTX_new();
            long s = NativeCrypto.SSL_new(c);
            try {
                NativeCrypto.SSL_write(s, INVALID_FD, DUMMY_CB, new byte[1], 0, 1, 0);
                fail();
            } catch (SSLException expected) {
            }
            NativeCrypto.SSL_free(s);
            NativeCrypto.SSL_CTX_free(c);
        }

        // positively tested by test_SSL_read
    }
