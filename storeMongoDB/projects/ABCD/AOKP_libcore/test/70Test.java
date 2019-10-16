    public void test_SSL_set_session_creation_enabled() throws Exception {
        try {
            NativeCrypto.SSL_set_session_creation_enabled(NULL, false);
            fail();
        } catch (NullPointerException expected) {
        }

        {
            long c = NativeCrypto.SSL_CTX_new();
            long s = NativeCrypto.SSL_new(c);
            NativeCrypto.SSL_set_session_creation_enabled(s, false);
            NativeCrypto.SSL_set_session_creation_enabled(s, true);
            NativeCrypto.SSL_free(s);
            NativeCrypto.SSL_CTX_free(c);
        }

        final ServerSocket listener = new ServerSocket(0);

        // negative test case for SSL_set_session_creation_enabled(false) on client
        try {
            Hooks cHooks = new Hooks() {
                @Override
                public long beforeHandshake(long c) throws SSLException {
                    long s = super.beforeHandshake(c);
                    NativeCrypto.SSL_set_session_creation_enabled(s, false);
                    return s;
                }
            };
            Hooks sHooks = new ServerHooks(getServerPrivateKey(), getServerCertificates());
            Future<TestSSLHandshakeCallbacks> client = handshake(listener, 0, true, cHooks, null,
                    null);
            Future<TestSSLHandshakeCallbacks> server = handshake(listener, 0, false, sHooks, null,
                    null);
            client.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
            fail();
        } catch (ExecutionException expected) {
            assertEquals(SSLProtocolException.class, expected.getCause().getClass());
        }

        // negative test case for SSL_set_session_creation_enabled(false) on server
        try {
            Hooks cHooks = new Hooks();
            Hooks sHooks = new ServerHooks(getServerPrivateKey(), getServerCertificates()) {
                @Override
                public long beforeHandshake(long c) throws SSLException {
                    long s = super.beforeHandshake(c);
                    NativeCrypto.SSL_set_session_creation_enabled(s, false);
                    return s;
                }
            };
            Future<TestSSLHandshakeCallbacks> client = handshake(listener, 0, true, cHooks, null,
                    null);
            Future<TestSSLHandshakeCallbacks> server = handshake(listener, 0, false, sHooks, null,
                    null);
            client.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
            fail();
        } catch (ExecutionException expected) {
            assertEquals(SSLProtocolException.class, expected.getCause().getClass());
        }
    }
