    public void test_SSL_get_certificate() throws Exception {
        try {
            NativeCrypto.SSL_get_certificate(NULL);
            fail();
        } catch (NullPointerException expected) {
        }

        final ServerSocket listener = new ServerSocket(0);
        Hooks cHooks = new Hooks() {
            @Override
            public void afterHandshake(long session, long s, long c,
                                       Socket sock, FileDescriptor fd,
                                       SSLHandshakeCallbacks callback)
                throws Exception {
                assertNull(NativeCrypto.SSL_get_certificate(s));
                super.afterHandshake(session, s, c, sock, fd, callback);
            }
        };
        Hooks sHooks = new ServerHooks(getServerPrivateKey(), getServerCertificates()) {
            @Override
            public void afterHandshake(long session, long s, long c,
                                       Socket sock, FileDescriptor fd,
                                       SSLHandshakeCallbacks callback)
                    throws Exception {
                assertEqualCertificateChains(
                                             getServerCertificates(),
                                             NativeCrypto.SSL_get_certificate(s));
                super.afterHandshake(session, s, c, sock, fd, callback);
            }
        };
        Future<TestSSLHandshakeCallbacks> client = handshake(listener, 0, true, cHooks, null, null);
        Future<TestSSLHandshakeCallbacks> server = handshake(listener, 0, false, sHooks, null, null);
        client.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        server.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }
