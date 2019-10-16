    public void test_SSL_do_handshake_clientCertificateRequested_throws_after_renegotiate()
            throws Exception {
        final ServerSocket listener = new ServerSocket(0);

        Hooks cHooks = new Hooks() {
            @Override
            public long beforeHandshake(long context) throws SSLException {
                long s = super.beforeHandshake(context);
                NativeCrypto.SSL_clear_mode(s, SSL_MODE_HANDSHAKE_CUTTHROUGH);
                return s;
            }
            @Override
            public void afterHandshake(long session, long s, long c,
                                       Socket sock, FileDescriptor fd,
                                       SSLHandshakeCallbacks callback)
                    throws Exception {
                NativeCrypto.SSL_read(s, fd, callback, new byte[1], 0, 1, 0);
                fail();
                super.afterHandshake(session, s, c, sock, fd, callback);
            }
            @Override
            public void clientCertificateRequested(long s) {
                super.clientCertificateRequested(s);
                throw new RuntimeException("expected");
            }
        };
        Hooks sHooks = new ServerHooks(getServerPrivateKey(), getServerCertificates()) {
            @Override
            public void afterHandshake(long session, long s, long c,
                                       Socket sock, FileDescriptor fd,
                                       SSLHandshakeCallbacks callback)
                    throws Exception {
                try {
                    NativeCrypto.SSL_set_verify(s, NativeCrypto.SSL_VERIFY_PEER);
                    NativeCrypto.SSL_set_options(
                            s, NativeCrypto.SSL_OP_NO_SESSION_RESUMPTION_ON_RENEGOTIATION);
                    NativeCrypto.SSL_renegotiate(s);
                    NativeCrypto.SSL_write(s, fd, callback, new byte[] { 42 }, 0, 1,
                                           (int) ((TIMEOUT_SECONDS * 1000) / 2));
                } catch (IOException expected) {
                } finally {
                    super.afterHandshake(session, s, c, sock, fd, callback);
                }
            }
        };
        Future<TestSSLHandshakeCallbacks> client = handshake(listener, 0, true, cHooks, null, null);
        Future<TestSSLHandshakeCallbacks> server = handshake(listener, 0, false, sHooks, null, null);
        try {
            client.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            if (!"expected".equals(e.getCause().getMessage())) {
                throw e;
            }
        }
        server.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }
