    public void test_SSL_NpnNegotiateSuccess() throws Exception {
        final byte[] clientNpnProtocols = new byte[] {
                8, 'h', 't', 't', 'p', '/', '1', '.', '1',
                3, 'f', 'o', 'o',
                6, 's', 'p', 'd', 'y', '/', '2',
        };
        final byte[] serverNpnProtocols = new byte[] {
                6, 's', 'p', 'd', 'y', '/', '2',
                3, 'f', 'o', 'o',
                3, 'b', 'a', 'r',
        };

        Hooks cHooks = new Hooks() {
            @Override public long beforeHandshake(long context) throws SSLException {
                NativeCrypto.SSL_CTX_enable_npn(context);
                return super.beforeHandshake(context);
            }
            @Override public void afterHandshake(long session, long ssl, long context, Socket socket,
                    FileDescriptor fd, SSLHandshakeCallbacks callback) throws Exception {
                byte[] negotiated = NativeCrypto.SSL_get_npn_negotiated_protocol(ssl);
                assertEquals("spdy/2", new String(negotiated));
                assertTrue("NPN should enable cutthrough on the client",
                        0 != (NativeCrypto.SSL_get_mode(ssl) & SSL_MODE_HANDSHAKE_CUTTHROUGH));
                super.afterHandshake(session, ssl, context, socket, fd, callback);
            }
        };
        Hooks sHooks = new ServerHooks(getServerPrivateKey(), getServerCertificates()) {
            @Override public long beforeHandshake(long context) throws SSLException {
                NativeCrypto.SSL_CTX_enable_npn(context);
                return super.beforeHandshake(context);
            }
            @Override public void afterHandshake(long session, long ssl, long c, Socket sock,
                    FileDescriptor fd, SSLHandshakeCallbacks callback) throws Exception {
                byte[] negotiated = NativeCrypto.SSL_get_npn_negotiated_protocol(ssl);
                assertEquals("spdy/2", new String(negotiated));
                assertEquals("NPN should not enable cutthrough on the server",
                        0, NativeCrypto.SSL_get_mode(ssl) & SSL_MODE_HANDSHAKE_CUTTHROUGH);
                super.afterHandshake(session, ssl, c, sock, fd, callback);
            }
        };

        ServerSocket listener = new ServerSocket(0);
        Future<TestSSLHandshakeCallbacks> client
                = handshake(listener, 0, true, cHooks, clientNpnProtocols, null);
        Future<TestSSLHandshakeCallbacks> server
                = handshake(listener, 0, false, sHooks, serverNpnProtocols, null);
        client.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        server.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }
