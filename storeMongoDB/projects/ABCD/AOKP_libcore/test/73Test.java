    public void test_SSL_AlpnNegotiateSuccess() throws Exception {
        final byte[] clientAlpnProtocols = new byte[] {
                8, 'h', 't', 't', 'p', '/', '1', '.', '1',
                3, 'f', 'o', 'o',
                6, 's', 'p', 'd', 'y', '/', '2',
        };
        final byte[] serverAlpnProtocols = new byte[] {
                6, 's', 'p', 'd', 'y', '/', '2',
                3, 'f', 'o', 'o',
                3, 'b', 'a', 'r',
        };

        Hooks cHooks = new Hooks() {
            @Override public long beforeHandshake(long context) throws SSLException {
                NativeCrypto.SSL_CTX_set_alpn_protos(context, clientAlpnProtocols);
                return super.beforeHandshake(context);
            }
            @Override public void afterHandshake(long session, long ssl, long context, Socket socket,
                    FileDescriptor fd, SSLHandshakeCallbacks callback) throws Exception {
                byte[] negotiated = NativeCrypto.SSL_get0_alpn_selected(ssl);
                assertEquals("spdy/2", new String(negotiated));
                /*
                 * There is no callback on the client, so we can't enable
                 * cut-through
                 */
                assertEquals("ALPN should not enable cutthrough on the client", 0,
                        NativeCrypto.SSL_get_mode(ssl) & SSL_MODE_HANDSHAKE_CUTTHROUGH);
                super.afterHandshake(session, ssl, context, socket, fd, callback);
            }
        };
        Hooks sHooks = new ServerHooks(getServerPrivateKey(), getServerCertificates()) {
            @Override public void afterHandshake(long session, long ssl, long c, Socket sock,
                    FileDescriptor fd, SSLHandshakeCallbacks callback) throws Exception {
                byte[] negotiated = NativeCrypto.SSL_get0_alpn_selected(ssl);
                assertEquals("spdy/2", new String(negotiated));
                assertEquals("ALPN should not enable cutthrough on the server",
                        0, NativeCrypto.SSL_get_mode(ssl) & SSL_MODE_HANDSHAKE_CUTTHROUGH);
                super.afterHandshake(session, ssl, c, sock, fd, callback);
            }
        };

        ServerSocket listener = new ServerSocket(0);
        Future<TestSSLHandshakeCallbacks> client
                = handshake(listener, 0, true, cHooks, null, null);
        Future<TestSSLHandshakeCallbacks> server
                = handshake(listener, 0, false, sHooks, null, serverAlpnProtocols);
        client.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        server.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }
